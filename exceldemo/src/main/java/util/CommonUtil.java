/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CommonUtil
 * Author:   Dell
 * Date:     2018/10/25 16:37
 * Description: 公共工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package util;

import oracle.sql.NUMBER;

import java.io.File;

/**
 * 〈Coding never to stop〉<br>
 * 〈公共工具类〉
 *
 * @author zombie
 * @create 2018/10/25
 * @since 1.0.0
 */
public class CommonUtil {

    /**
     * 每次提交的事务数据量
     */
    private final static int DEFAULT_TRANSCATION_COMMIT_NUMBER = 20000;

    /**
     * 默认线程数
     */
    private static final int DEFAULT_THREAD_NUM = 10;

    public enum ExcelFileType {

        /** office 2003 版本的excel 文件类型后缀名*/
        EXCEL_2003("Excel 2003版文件类型","xls"),

        /** office 2007+ 版本的excel 文件类型后缀名 */
        EXCEL_2007("Excel 2007版文件类型","xlsx");

        private final String name;

        private final String suffix;

        ExcelFileType(String name,String suffix){
            this.name = name;
            this.suffix = suffix;
        }

        public String getName(){
            return name;
        }

        public String getSuffix(){
            return suffix;
        }

    }

    /**
     * 判断字符串是否为空
     * 若字符串为:null 则为true
     * 若字符串为:"" 则为true
     * 若字符串包含空格字符串如:" ",也为true
     * 若字符串为:"  aa ",则为false
     *
     * @return true:字符串为空 ，反之false
     */
    public static boolean isBlank(String args) {
        return null == args || "".equals(args.trim());
    }

    /**
     * 判断字符串是否不为空
     *
     * @param args 字符串
     * @return 若当前字符串不为空则为 true , 反之false
     */
    public static boolean isNotBlank(String args) {
        return !isBlank(args);
    }

    /**
     * 获取Excel 2003 版本文件类型
     *
     * @return 返回 Excel2003 版的文件类型
     */
    public static String getExcel03FileType() {
        return String.valueOf(ExcelFileType.EXCEL_2003.getSuffix());
    }

    /**
     * 获取Excel 2007+ 版本文件类型
     *
     * @return 返回 Excel2007+ 版的文件类型
     */
    public static String getExcel07FileType() {
        return String.valueOf(ExcelFileType.EXCEL_2007.getSuffix());
    }


    /**
     * 获取Excel 文件类型
     *
     * @param filePath 文件路径
     * @return 当前文件的文件类型，若传入的文件为空，则返回空字符串""
     */
    public static String getFileType(String filePath) {
        if (isBlank(filePath)) {
            System.out.println("The  file must not be empty!");
            return "";
        }
        return filePath.substring(filePath.lastIndexOf(".") + 1);
    }

    /**
     * 判读是否为Office 2007+版本，Excel文件后缀名为“.xlsx”
     *
     * @param fileAbsolutePath excel 文件绝对路径
     * @return 若true，则为2007+版本Excel,false 则不是该版本，
     * 注意 false 不一定代表为其他版本，如文件不存在也为false
     */
    public static boolean isXlsxExcel(String fileAbsolutePath) {
        return getExcel07FileType().equals(getFileType(fileAbsolutePath));
    }

    /**
     * 判读是否为Office 2003版本，Excel文件后缀名为“.xlsx”
     *
     * @param fileAbsolutePath excel 文件绝对路径
     * @return 若true，则为2003版本Excel,false 则不是该版本，
     * 注意 false 不一定代表为其他版本，如文件不存在也为false
     */
    public static boolean isXlsExcel(String fileAbsolutePath) {
        return getExcel03FileType().equals(getFileType(fileAbsolutePath));
    }

    /**
     * 判断文件是否存在
     *
     * @param fileAbsolutePath 文件路径
     * @return 若文件存在则为 true, 反之false
     */
    public static boolean existFile(String fileAbsolutePath) {
        boolean exists = false;
        if (isNotBlank(fileAbsolutePath)) {
            File file = new File(fileAbsolutePath);
            exists = file.exists();
        }
        return exists;
    }

    /**
     * 文件不存在，true:表示不存在，反之，false
     *
     * @param fileAbsolutePath 文件路径
     * @return 若文件不存在，则为true ,反之,false
     */
    public static boolean notExistsFile(String fileAbsolutePath) {
        return !existFile(fileAbsolutePath);
    }

    /**
     * 获取一次默认提交的事务数
     *
     * @return 默认每次提交的事务数
     */
    public static int getDefaultTranscationCommitNumber() {
        return DEFAULT_TRANSCATION_COMMIT_NUMBER;
    }

    public static int getDefaultThreadNum() {
        return DEFAULT_THREAD_NUM;
    }


}
