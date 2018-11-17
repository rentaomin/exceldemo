/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RowProperties
 * Author:   Dell
 * Date:     2018/10/25 14:52
 * Description: Excel 行实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈Coding never to stop〉<br>
 * 〈Excel 行实体类〉
 * 用于配置Excel 行的各种属性
 *
 * @author zombie
 * @create 2018/10/25
 * @since 1.0.0
 */
public class RowProperties implements Serializable {

    /**
     * 行下标
     */
    private int rowIndex;

    /**
     * 总行数
     */
    private int totalRows;

    /**
     * sheet 页名称
     */
    private String sheetName;

    /**
     * 存储当前行所有单元格内容，即当前行数据
     */
    private List<CellProperties> cellPropertiesList = new ArrayList<CellProperties>();

    /**
     * 中断标识，用于标识当前行是否为解析Excel数据的最后行记录，默认为 false ,
     * 若为解析的excel文件的最后一行则为 true,若有多个sheet页，该标记会在
     * 解析完最后一个sheet页，最后一条行记录设置为 true
     * <p>
     * 若 true ，则终止当前线程任务，false,则继续执行任务
     */
    private boolean isTerminated = false;


    public RowProperties() {
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<CellProperties> getCellPropertiesList() {
        return cellPropertiesList;
    }

    public void setCellPropertiesList(List<CellProperties> cellPropertiesList) {
        this.cellPropertiesList = cellPropertiesList;
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
    }

    @Override
    public String toString() {
        return "RowProperties{" +
                "rowIndex=" + rowIndex +
                ", totalRows=" + totalRows +
                ", sheetName='" + sheetName + '\'' +
                ", cellPropertiesList=" + cellPropertiesList +
                ", isTerminated=" + isTerminated +
                '}';
    }
}
