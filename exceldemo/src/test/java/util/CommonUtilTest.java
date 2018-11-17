/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CommonUtilTest
 * Author:   Dell
 * Date:     2018/11/4 17:08
 * Description: 公共工具类测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package util;

import org.junit.Assert;
import org.junit.Test;

/**
 * 〈Coding never to stop〉<br>
 * 〈公共工具类测试类〉
 *
 * @author zombie
 * @create 2018/11/4
 * @since 1.0.0
 */
public class CommonUtilTest {


    @Test
    public void testIsBlank() {
        // 测试空字符串
        String arg0 = "";
        Assert.assertTrue(CommonUtil.isBlank(arg0));
        String arg1 = " ";
        Assert.assertTrue(CommonUtil.isBlank(arg1));

        // 测试 null
        String arg2 = null;
        Assert.assertTrue(CommonUtil.isBlank(arg2));

        // 测试不为空的字符串
        String arg3 = " aa ";
        Assert.assertFalse(CommonUtil.isBlank(arg3));
    }

    @Test
    public void testIsNotBlank() {
        // 测试空字符串
        String arg0 = "";
        Assert.assertFalse(CommonUtil.isNotBlank(arg0));
        String arg1 = " ";
        Assert.assertFalse(CommonUtil.isNotBlank(arg1));

        // 测试 null
        String arg2 = null;
        Assert.assertFalse(CommonUtil.isNotBlank(arg2));

        // 测试不为空的字符串
        String arg3 = " aa ";
        Assert.assertTrue(CommonUtil.isNotBlank(arg3));
    }

    @Test
    public void testGetExcel03FileType() {
        String excel03FileType = "xls";
        Assert.assertEquals(excel03FileType, CommonUtil.getExcel03FileType());
    }

    @Test
    public void testGetExcel07FileType() {
        String excel07FileType = "xlsx";
        Assert.assertEquals(excel07FileType, CommonUtil.getExcel07FileType());
    }

    @Test
    public void testGetExcelFileType() {
        // 测试excel 2007 版
        String excel2007File = "test.xlsx";
        Assert.assertEquals("xlsx", CommonUtil.getFileType(excel2007File));

        // 测试excel 2003版
        String excel2003File = "test.xls";
        Assert.assertEquals("xls", CommonUtil.getFileType(excel2003File));

        String exceFile = "aa.txt";
        Assert.assertEquals("txt", CommonUtil.getFileType(exceFile));

        String file = "";
        Assert.assertEquals("", CommonUtil.getFileType(file));

        String file2 = null;
        Assert.assertEquals("", CommonUtil.getFileType(file2));
    }

    @Test
    public void isXlsxExcelTest() {
        String filepath = "a.xlsx";
        Assert.assertTrue(CommonUtil.isXlsxExcel(filepath));

        filepath = "b.txt";
        Assert.assertFalse(CommonUtil.isXlsxExcel(filepath));

        filepath = "";
        Assert.assertFalse(CommonUtil.isXlsxExcel(filepath));

        filepath = "  ";
        Assert.assertFalse(CommonUtil.isXlsxExcel(filepath));

        filepath = null;
        Assert.assertFalse(CommonUtil.isXlsxExcel(filepath));
    }

    @Test
    public void isXlsExcelTest() {
        String filepath = "a.xls";
        Assert.assertTrue(CommonUtil.isXlsExcel(filepath));

        filepath = "b.txt";
        Assert.assertFalse(CommonUtil.isXlsExcel(filepath));

        filepath = "";
        Assert.assertFalse(CommonUtil.isXlsExcel(filepath));

        filepath = "  ";
        Assert.assertFalse(CommonUtil.isXlsExcel(filepath));

        filepath = null;
        Assert.assertFalse(CommonUtil.isXlsExcel(filepath));
    }

    @Test
    public void existFileTest() {
        String filepath = "a.xls";
        Assert.assertFalse(CommonUtil.existFile(filepath));

        filepath = "";
        Assert.assertFalse(CommonUtil.existFile(filepath));

        filepath = null;
        Assert.assertFalse(CommonUtil.existFile(filepath));

        filepath = "F:\\pay.xlsx";
        Assert.assertTrue(CommonUtil.existFile(filepath));
    }

    @Test
    public void notExistsFileTest() {
        String filepath = "a.xls";
        Assert.assertTrue(CommonUtil.notExistsFile(filepath));

        filepath = "";
        Assert.assertTrue(CommonUtil.notExistsFile(filepath));

        filepath = "F:\\pay.xlsx";
        Assert.assertFalse(CommonUtil.notExistsFile(filepath));
    }


}
