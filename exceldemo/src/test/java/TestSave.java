/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestSave
 * Author:   Dell
 * Date:     2018/10/25 19:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import util.ExcelParseUtil;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 〈Coding never to stop〉<br>
 * 〈〉
 *
 * @author zombie
 * @create 2018/10/25
 * @since 1.0.0
 */
public class TestSave {

    public static void main(String[] args) throws FileNotFoundException {
        String xlsFile2Sheet = "F:/paylistinfo_hb.xls";

        String xlsFile = "F:/paylistinfo_hb22.xls";

        String xlsxFile = "F:/pay.xlsx";
        ExcelParseUtil excelParseUtil = new ExcelParseUtil();
        excelParseUtil.parseExcel(xlsFile);

    }

}
