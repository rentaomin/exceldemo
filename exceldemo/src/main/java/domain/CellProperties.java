/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CellProperties
 * Author:   Dell
 * Date:     2018/10/25 14:49
 * Description: 单元格实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package domain;

import java.io.Serializable;

/**
 * 〈Coding never to stop〉<br>
 * 〈单元格实体类〉
 * <p>
 * 用于存放和获取单元格的属性
 *
 * @author zombie
 * @create 2018/10/25
 * @since 1.0.0
 */
public class CellProperties implements Serializable {

    /**
     * 单元格对应列标题
     */
    private Object key;

    /**
     * 单元格内容值
     */
    private String value;

    /**
     * 数据类型
     */
    private Object dataType;

    /**
     * 行下标
     */
    private int rowIndex;

    /**
     * 列下标
     */
    private int columnIndex;

    /**
     * 当前单元格所在位置，用于Excel 2007+版本
     */
    private String position;

    /** 下述为Excel全局属性 */

    /**
     * 总行数
     */
    private int totalRows;

    /**
     * 总列数
     */
    private int totalColumns;

    /**
     * 当前sheet页数
     */
    private int sheetPage;

    /**
     * sheet页名称
     */
    private String sheetName;

    /**
     * 总sheet页数
     */
    private int totalSheetPage;

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getDataType() {
        return dataType;
    }

    public void setDataType(Object dataType) {
        this.dataType = dataType;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public int getSheetPage() {
        return sheetPage;
    }

    public void setSheetPage(int sheetPage) {
        this.sheetPage = sheetPage;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public int getTotalSheetPage() {
        return totalSheetPage;
    }

    public void setTotalSheetPage(int totalSheetPage) {
        this.totalSheetPage = totalSheetPage;
    }

    public CellProperties() {
    }

    /**
     * 用于存储excel 2007+版本单元格内容
     *
     * @param key
     * @param value
     */
    public CellProperties(Object key, String value, String position) {
        this.key = key;
        this.value = value;
        this.position = position;
    }

    /**
     * 用于Excel 2007+版本获取行的位置
     *
     * @param cell
     * @return
     */
    public int getLevel(CellProperties cell) {

        char[] other = String.valueOf(cell.key).replaceAll("[0-9]", "").toCharArray();
        char[] self = String.valueOf(this.key).replaceAll("[0-9]", "").toCharArray();
        if (other.length != self.length) return -1;
        for (int i = 0; i < other.length; i++) {
            if (i == other.length - 1) {
                return self[i] - other[i];
            } else {
                if (self[i] != other[i]) {
                    return -1;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "CellProperties{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", dataType=" + dataType +
                ", rowIndex=" + rowIndex +
                ", columnIndex=" + columnIndex +
                ", totalRows=" + totalRows +
                ", totalColumns=" + totalColumns +
                ", sheetPage=" + sheetPage +
                ", sheetName='" + sheetName + '\'' +
                ", totalSheetPage=" + totalSheetPage +
                '}';
    }
}
