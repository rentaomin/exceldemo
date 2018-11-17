/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ExcelParseUtil
 * Author:   Dell
 * Date:     2018/10/25 15:41
 * Description: Excel解析工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package util;

import handle.ThreadExecutor;

import java.io.FileNotFoundException;

/**
 * 〈Coding never to stop〉<br>
 * 〈Excel解析工具类〉
 *
 * @author zombie
 * @create 2018/10/25
 * @since 1.0.0
 */
public class ExcelParseUtil {


    /**
     * 解析传入的 Excel 文件：
     * 若为Excel 03版，则调用解析03版的excel 方法
     * 若为 Excel 07+版，则调用解析07版的excel 方法
     *
     * @param excelFilePath excel文件的绝对路径
     * @throws FileNotFoundException 当前路径不存在该文件
     */
    public void parseExcel(String excelFilePath) throws FileNotFoundException {

        ThreadExecutor threadExecutor = new ThreadExecutor();

        threadExecutor.executeTask(excelFilePath);

    }

}
