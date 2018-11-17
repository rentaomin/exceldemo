/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: XlsExcelParse
 * Author:   Dell
 * Date:     2018/10/25 16:32
 * Description: 处理Excel 03版的工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package handle;

import domain.CellProperties;
import domain.RowProperties;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import thread.pattern.CacheQueue;
import thread.pattern.ExcelParser;
import util.CommonUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 〈Coding never to stop〉<br>
 * 〈处理Excel 03版的工具类〉
 * <p>
 * 具体处理Excel 2003 版的工具类
 *
 * @author zombie
 * @create 2018/10/25
 * @since 1.0.0
 */
public class XlsExcelParser implements ExcelParser {

    /**
     * 解析的Excel的总Sheet页数
     */
    private int totalSheetNum = 0;

    private int totalRowNum;

    /**
     * Excel 单元格数据类型
     */
    private Object cellDataType;

    /**
     * 当前sheet页数
     */
    private int currentSheetPage;

    /**
     * 总列数
     */
    private int totalColumnNum;

    /**
     * sheet 页名称
     */
    private String sheetName;

    /**
     * sheet 页标题
     */
    private final Map<Object, Object> headerMap = new HashMap<Object, Object>();

    /**
     * 所有sheet页数据量
     */
    private Map<Object, List> dataMap = new HashMap<Object, List>();

    /**
     * 单个sheet 页数据内容
     */
    private List<RowProperties> dataList = new ArrayList<RowProperties>();

    /**
     * 消息缓存队列
     */
    private CacheQueue<RowProperties> cacheQueue;

    public XlsExcelParser(CacheQueue<RowProperties> cacheQueue) {
        this.cacheQueue = cacheQueue;
    }

    /**
     * 解析传入的指定的 excel 文件
     *
     * @param fileAbsolutePath 需要解析excel文件的绝对路径
     */
    public void parseExcel(String fileAbsolutePath) {
        try {
            long start = System.currentTimeMillis();

            HSSFWorkbook workbook = this.getWorkbook(fileAbsolutePath);
            totalSheetNum = workbook.getNumberOfSheets();
            for (int sheetNum = 0; sheetNum < totalSheetNum; sheetNum++) {
                this.currentSheetPage = sheetNum;
                HSSFSheet sheet = workbook.getSheetAt(sheetNum);

                parseSheet(sheet);
            }

            long end = System.currentTimeMillis();
            System.out.println("当前解析所有sheet页共花费：" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定excel文件的工作簿
     *
     * @param fileAbsolutePath excel文件路径
     * @return excel的工作簿
     */
    private HSSFWorkbook getWorkbook(String fileAbsolutePath) throws Exception {
        if (CommonUtil.isBlank(fileAbsolutePath)) {
            throw new FileNotFoundException("当前文件不存在！");
        }
        FileInputStream fileInputStream = new FileInputStream(new File(fileAbsolutePath));
        return new HSSFWorkbook(fileInputStream);
    }

    /**
     * 解析sheet页
     *
     * @param sheet 当前sheet页
     */
    private void parseSheet(HSSFSheet sheet) {
        long start = System.currentTimeMillis();

        sheetName = sheet.getSheetName();
        // 获取实际的行数，包括内容中间的空行
        totalRowNum = sheet.getPhysicalNumberOfRows();

        for (int rowNum = 0; rowNum < totalRowNum; rowNum++) {
            HSSFRow row = sheet.getRow(rowNum);
            if (this.isFirstRow(rowNum)) {
                parseExcelHeader(row);
            } else {
                parserExcelRow(row, rowNum);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("解析所有行花费时间：" + (end - start));
    }


    /***
     * 解析Excel row
     * @param row 被解析的行对象
     * @param rowIndex 行下标
     */
    private void parserExcelRow(HSSFRow row, int rowIndex) {

        List<CellProperties> storeCellPropertiesList = parseAndExtractExcelCell(row, rowIndex);

        RowProperties rowProperties = setRowData(rowIndex, storeCellPropertiesList);

        // 若为最后一条数据，则设置结束标记
        if (isLastRow(rowIndex)) {
            rowProperties.setTerminated(true);
            System.out.println("当前生产数据为最后一条！" + rowIndex);
        }

        // 生产者生产数据
        cacheQueue.push(rowProperties);
    }

    /**
     * 判断当前行是否为解析文件的最后一行
     * 判断条件：若当前shhet页为解析文件的最后一个sheet页，并且行下标与总行数相等，
     * 因为rowIndex 从0开始，所以总行数需要 totalRowNum-1
     *
     * @param rowIndex 行下标索引
     * @return 若true, 则表示当前文件解析完毕，false 反之。
     */
    private boolean isLastRow(int rowIndex) {
        return (totalSheetNum == currentSheetPage + 1 && rowIndex == totalRowNum - 1);
    }

    /**
     * 设置行数据
     *
     * @param rowIndex                行下标
     * @param storeCellPropertiesList 行数据内容
     * @return
     */
    private RowProperties setRowData(int rowIndex, List<CellProperties> storeCellPropertiesList) {
        RowProperties rowProperties = new RowProperties();
        rowProperties.setCellPropertiesList(storeCellPropertiesList);
        rowProperties.setRowIndex(rowIndex);
        rowProperties.setTotalRows(this.totalRowNum);
        rowProperties.setSheetName(sheetName);
        return rowProperties;
    }

    /**
     * 解析当前行的 Excel单元格并且获取每行的数据内容
     *
     * @param row      具体行对象
     * @param rowIndex 行下标数
     */
    private List<CellProperties> parseAndExtractExcelCell(HSSFRow row, int rowIndex) {

        List<CellProperties> storeCellPropertiesList = new ArrayList<CellProperties>(totalColumnNum);

        for (int cellIndex = 0; cellIndex < totalColumnNum; cellIndex++) {
            HSSFCell hssfCell = row.getCell(cellIndex);
            // 获取单元格内容
            String cellVal = this.formatValue(hssfCell);

            CellProperties cellProperties = setCellData(cellVal, rowIndex, cellIndex);

            storeCellPropertiesList.add(cellProperties);
        }
        return storeCellPropertiesList;
    }

    /**
     * 设置单元格内容
     *
     * @param cellVal   单元格的内容
     * @param rowIndex  行下标索引
     * @param cellIndex 单元格列下标索引
     * @return 单元格实体类
     */
    private CellProperties setCellData(String cellVal, int rowIndex, int cellIndex) {
        CellProperties cellProperties = new CellProperties();
        cellProperties.setKey(headerMap.get(cellIndex));
        cellProperties.setValue(cellVal);
        cellProperties.setDataType(cellDataType);
        cellProperties.setRowIndex(rowIndex);
        cellProperties.setColumnIndex(cellIndex);
        cellProperties.setTotalSheetPage(totalSheetNum);
        cellProperties.setSheetPage(currentSheetPage);
        cellProperties.setSheetName(sheetName);
        return cellProperties;
    }

    /**
     * 判断当前行是否为第一行数据
     *
     * @param rowNum 行下标号，从0开始，第一行即该下标值为0
     * @return true: 第一行，反之 false
     */
    private boolean isFirstRow(int rowNum) {
        return rowNum == 0;
    }

    /**
     * 获取第一行标题信息
     *
     * @param firstRow
     */
    private void parseExcelHeader(HSSFRow firstRow) {
        totalColumnNum = firstRow.getLastCellNum();
        for (int cellNum = 0; cellNum < totalColumnNum; cellNum++) {
            HSSFCell hssfCell = firstRow.getCell(cellNum);
            String cellVal = this.formatValue(hssfCell);
            headerMap.put(cellNum, cellVal);
        }
    }

    /**
     * 对excel 单元格内容的不同数据类型进行格式化
     * <p>
     * 单元格内容数据类型具体如：
     * 若为 null,则返回空字符串""
     * 若为 STRING ，则返回 getStringCellValue()
     * 若为 NUMERIC ，
     * a：为日期类型，则按照 "yyyy-MM-dd HH:mm:ss"进行格式化，返回该格式的日期字符串
     * b: 为数字类型，则对数字进行格式化取值
     * 若为 BLANK，则返回 空字符串""
     * 若为 FORMULA (公式) 则返回getCellFormula()的字符串
     * 若为 BOOLEAN ，则返回getBooleanCellValue()的字符串
     * 若为 ERROR，则默认返回值为 "非法字符"
     * 若为 其它，则默认返回值为 "未知类型";
     *
     * @param hssfCell excel 单元格对象
     * @return 单元格内容进行格式化后的字符串
     */
    private String formatValue(HSSFCell hssfCell) {
        String cellVal = "";
        if (hssfCell == null) {
            return cellVal;
        }
        CellType clleType = hssfCell.getCellTypeEnum();
        switch (clleType) {
            case STRING:
                cellVal = hssfCell.getStringCellValue();
                this.cellDataType = "STRING";
                break;
            case NUMERIC:
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(hssfCell)) {
                    Date dateCellValue = hssfCell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cellVal = String.valueOf(sdf.format(HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue())));
                    this.cellDataType = "Date";
                } else {
                    cellVal = new DecimalFormat("0").format(hssfCell.getNumericCellValue());
                    this.cellDataType = "Numberic";
                }
                break;
            case BLANK:
                cellVal = "";
                this.cellDataType = "BLANK";
                break;
            case BOOLEAN:
                cellVal = String.valueOf(hssfCell.getBooleanCellValue());
                this.cellDataType = "BOOLEAN";
                break;
            case FORMULA:
                cellVal = String.valueOf(hssfCell.getCellFormula());
                this.cellDataType = "FORMULA";
                break;
            case ERROR:
                cellVal = "非法字符";
                this.cellDataType = "ERROR";
                break;
            default:
                cellVal = "未知类型";
                this.cellDataType = "未知类型";
                break;
        }
        return cellVal;
    }

    public int getTotalSheetNum() {
        return totalSheetNum;
    }

    public void setTotalSheetNum(int totalSheetNum) {
        this.totalSheetNum = totalSheetNum;
    }

    public int getTotalRowNum() {
        return totalRowNum;
    }

    public void setTotalRowNum(int totalRowNum) {
        this.totalRowNum = totalRowNum;
    }

    public Object getCellDataType() {
        return cellDataType;
    }

    public void setCellDataType(Object cellDataType) {
        this.cellDataType = cellDataType;
    }

    public int getCurrentSheetPage() {
        return currentSheetPage;
    }

    public void setCurrentSheetPage(int currentSheetPage) {
        this.currentSheetPage = currentSheetPage;
    }

    public int getTotalColumnNum() {
        return totalColumnNum;
    }

    public void setTotalColumnNum(int totalColumnNum) {
        this.totalColumnNum = totalColumnNum;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
}
