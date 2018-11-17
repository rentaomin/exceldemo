/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ExcelParser
 * Author:   zombie
 * Date:     2018/11/9 14:40
 * Description: Excel解析器接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package thread.pattern;

/**
 * 〈Coding never to stop〉<br> 
 * 〈Excel解析器接口〉
 *
 * @author zombie
 * @create 2018/11/9
 * @since 1.0.0
 */
public interface ExcelParser {

    /**
     *  解析Excel 文件
     * @param excelFilePath excel 文件路径
     */
    void parseExcel(String excelFilePath);

}
