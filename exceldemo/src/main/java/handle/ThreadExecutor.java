/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ThreadExecutor
 * Author:   Dell
 * Date:     2018/10/29 14:57
 * Description: 用于控制生产者和消费者线程的执行和终止
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package handle;

import thread.pattern.BlockCacheQueue;
import thread.pattern.CacheQueue;
import thread.excel.ExcelProducer;
import thread.excel.ImportDBConsumer;
import util.CommonUtil;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 〈Coding never to stop〉<br>
 * 〈用于控制生产者和消费者线程的执行和终止〉
 *
 * @author zombie
 * @create 2018/10/29
 * @since 1.0.0
 */
public class ThreadExecutor {

    /**
     * 产生生产者和消费者执行任务
     */
    public void executeTask(String fileAbsolutePath) throws FileNotFoundException {
        if (!CommonUtil.existFile(fileAbsolutePath)) {
            throw new FileNotFoundException("文件不存在！");
        }

        long start = printConsoleStartTime();

        CacheQueue cacheQueue = new BlockCacheQueue(new LinkedBlockingQueue(20000));

        //  线程管理器
        ExecutorService executorService = Executors.newFixedThreadPool(CommonUtil.getDefaultThreadNum());

        // 一个生产者
        ExcelProducer producerThread = new ExcelProducer(cacheQueue, fileAbsolutePath);


        // 一个消费者
        ImportDBConsumer importDBConsumer = new ImportDBConsumer(cacheQueue);


        // 分别提交执行任务
        executorService.submit(producerThread);
        executorService.submit(importDBConsumer);

        // 结束执行任务
        executorService.shutdown();

        System.out.println(Thread.currentThread().getName() + "当前花费时间：" + (System.currentTimeMillis() - start));
    }

    /**
     * 打印开始时间到控制台
     */
    private long  printConsoleStartTime(){
        long start = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("时间：" + df.format(new Date()) + "开始时间：" + start);
        return start;
    }
}
