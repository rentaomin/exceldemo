/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ExcelProducer
 * Author:   Dell
 * Date:     2018/10/25 18:33
 * Description: Excel 生产者
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package thread.excel;

import domain.RowProperties;
import handle.XlsExcelParser;
import handle.XlsxExcelParser;
import thread.pattern.CacheQueue;
import thread.pattern.AbstractProducer;
import thread.pattern.ExcelParser;
import thread.pattern.Producer;
import util.CommonUtil;

/**
 * 〈Coding never to stop〉<br>
 * 〈Excel 生产者〉
 *
 * @author zombie
 * @create 2018/10/25
 * @since 1.0.0
 */
public class ExcelProducer extends AbstractProducer {

    /**
     * 消息缓存队列
     */
    private  CacheQueue<RowProperties> cacheQueue;

    /**
     * 文件绝对路径
     */
    private final String filePath;

    public ExcelProducer(CacheQueue cacheQueue, String filePath) {
        this.cacheQueue = cacheQueue;
        this.filePath = filePath;
    }

    /**
     * 开始执行生产者任务
     */
    public void produce() {
        long start = System.currentTimeMillis();

        if (CommonUtil.isXlsExcel(filePath)) {

            // 调用Excel 2003版本解析工具类方法
            ExcelParser xlsExcelParse = new XlsExcelParser(cacheQueue);
            xlsExcelParse.parseExcel(filePath);
        } else if (CommonUtil.isXlsxExcel(filePath)) {
            // 调用Excel 2007+版本解析工具类方法
            ExcelParser xlsxExcelParse = new XlsxExcelParser(cacheQueue);
            xlsxExcelParse.parseExcel(filePath);
        }

        System.out.println(Thread.currentThread().getName() + "当前解析excel花费时间：" + (System.currentTimeMillis() - start));
    }

    public Producer createProducer() {
        return null;
    }

}
