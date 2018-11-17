/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: XlsxExcelParse
 * Author:   Dell
 * Date:     2018/10/26 9:57
 * Description: Excel 2007+版本工具解析类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package handle;

import domain.CellProperties;
import domain.RowProperties;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import thread.pattern.CacheQueue;
import thread.pattern.ExcelParser;
import util.CommonUtil;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 〈Coding never to stop〉<br>
 * 〈Excel 2007+版本工具解析类〉
 *
 * @author zombie
 * @create 2018/10/26
 * @since 1.0.0
 */
public class XlsxExcelParser implements ExcelParser {

    // 起始行号,默认为第一行
    private final int startRow = 1;
    // 结束行号，默认处理的Excel最大 数据量
    private final int endRow = 1048576;
    // 当前行号
    private int currentRow = 0;
    // 行对象
    private RowProperties rowProperties;
    // excel 2007+ 版本文件类型
    private String excelType = CommonUtil.getExcel07FileType();

    /**
     * sheet 页标题
     */
    private final Map<Object, Object> headerMap = new HashMap<Object, Object>();

    /**
     * 列下表索引
     */
    private int columnIndex = 0;

    private boolean isNewRow = true;

    /**
     * 消息缓存队列
     */
    private CacheQueue<RowProperties> cacheQueue;

    public XlsxExcelParser(CacheQueue<RowProperties> cacheQueue) {
        this.cacheQueue = cacheQueue;
    }

    private XlsxExcelParser(String excelType) {
        this.excelType = excelType;
    }

    public XlsxExcelParser getInstance(String excelType){
        return new XlsxExcelParser(excelType);
    }

    public CacheQueue getCacheQueue() {
        return cacheQueue;
    }

    public CacheQueue setCacheQueue() {
        return cacheQueue;
    }

    /**
     * 解析excel 文件
     *
     * @throws Exception
     */
    public void parseExcel(String filePath) {
        if (CommonUtil.notExistsFile(filePath)) {
            System.out.println("当前文件不存在！");
        } else {
            // 处理excel 文件
            processFirstSheet(filePath);
        }
    }

    /**
     * 指定获取第一个sheet
     *
     * @throws Exception
     */
    private void processFirstSheet(String filePath) {
        try {
            OPCPackage pkg = OPCPackage.open(filePath);
            XSSFReader r = new XSSFReader(pkg);
            SharedStringsTable sst = r.getSharedStringsTable();
            XMLReader parser = fetchSheetParser(sst);
            // To look up the Sheet Name / Sheet Order / rID,
            //  you need to process the core Workbook stream.
            // Normally it's of the form rId# or rSheet#
            // rId1 sheet 页，若多个sheet页可在此处调整该值
            InputStream sheet1 = r.getSheet("rId1");
            InputSource sheetSource = new InputSource(sheet1);
            parser.parse(sheetSource);
            sheet1.close();
        } catch (Exception e) {
            System.out.println("解析文件出错！");
            e.printStackTrace();
        }
    }

    /**
     * 使用解析器，自定义处理规则
     *
     * @param sst
     * @return
     * @throws SAXException
     */
    private XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
        XMLReader parser =
                XMLReaderFactory.createXMLReader(
                        "org.apache.xerces.parsers.SAXParser"
                );
        ContentHandler handler = new PagingHandler(sst);
        parser.setContentHandler(handler);
        return parser;
    }

    /**
     * 实现自己的处理逻辑
     * See org.xml.sax.helpers.DefaultHandler javadocs
     */
    private class PagingHandler extends DefaultHandler {
        /**
         * excel 常量数据对象，对应的就是sharedStrings.xml文件中的内容，类似excel中的常量池
         */
        private final SharedStringsTable sst;
        /**
         * 当前处理的文本值
         */
        private String lastContents;
        /**
         * 下一个文本是不是String类型
         */
        private boolean nextIsString;
        /**
         * 当前单元格的索引值，从0开始，0:第一列
         */
        private String index = null;

        private PagingHandler(SharedStringsTable sst) {
            this.sst = sst;
        }

        /**
         * 每个单元格开始时的处理
         */
        @Override
        public void startElement(String uri, String localName, String name,
                                 Attributes attributes) {
            // c 表示单元格
            if (name.equals("c")) {
                // Print the cell reference
                //	System.out.print(attributes.getValue("r") + " - ");
                index = attributes.getValue("r");
                // index 表示Excel 位置，如 A1，A2,B1...
            /*    if(index.contains("N")){
                    System.out.println("##"+attributes+"##");
                }*/

                // 判断当前行是否为新的一行数据，若是则存储上一行数据
                if (Pattern.compile("^A[0-9]+$").matcher(index).find()) {
                    //存储上一行数据
                    if (rowProperties != null && !fistRow(currentRow)) {
                        // 生产者生产数据
                        rowProperties.setRowIndex(currentRow);
                        cacheQueue.push(rowProperties);
                    }
                    rowProperties = new RowProperties();
                    //因为从0开始，所以当前行为：currentRow+1，
                    currentRow++;
                }
                if (isAccess()) {
                    // Figure out if the value is an index in the SST
                    // t 表示单元格的数据类型 ，s 表示为字符串
                    String cellType = attributes.getValue("t");
                    nextIsString = cellType != null && cellType.equals("s");
                }
            }
            // 清空当前单元格内容
            lastContents = "";
        }

        /**
         * 判断是否为第一行内容
         *
         * @param currentRow 当前行号
         * @return
         */
        private boolean fistRow(int currentRow) {
            return 0 == currentRow || 1 == currentRow;
        }

        /**
         * 每个单元格结束时的处理
         */
        @Override
        public void endElement(String uri, String localName, String name) {
            if (isAccess()) {
                // Process the last contents as required.
                // Do now, as characters() may be called more than once
                if (nextIsString) {
                    int idx = Integer.parseInt(lastContents);
                    lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
                    nextIsString = false;
                }
                // v 表示的是单元格内容
                if (name.equals("v")) {
                    if (currentRow == 1) {
                        headerMap.put(columnIndex, lastContents);
                        columnIndex++;
                    } else {
                        // 如过为新的一行，则重置列索引
                        if (isNewRow) {
                            columnIndex = 0;
                            isNewRow = false;
                        }
                        Object key = headerMap.get(columnIndex);
                        rowProperties.getCellPropertiesList().add(new CellProperties(key, lastContents, index));
                        columnIndex++;
                    }
                }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            if (isAccess()) {
                lastContents += new String(ch, start, length);
            }

        }

        /**
         * 如果文档结束后，发现读取的末尾行正处在当前行中，存储下这行
         * （存在这样一种情况，当待读取的末尾行正好是文档最后一行时，最后一行无法存到集合中，
         * 因为最后一行没有下一行了，所以不为启动starElement()方法，
         * 当然我们可以通过指定最大列来处理，但不想那么做，扩展性不好）
         */
        @Override
        public void endDocument() {
            if (isAccess()) {
                // 该数据为最后一条
                rowProperties.setTerminated(true);
                cacheQueue.push(rowProperties);
            }
        }
    }

    /**
     * 判断是否存在可以解析的行
     *
     * @return
     */
    private boolean isAccess() {
        if (currentRow >= startRow && currentRow <= endRow) {
            return true;
        }
        return false;
    }

}
