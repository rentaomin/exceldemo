/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ImportDBConsumer
 * Author:   Dell
 * Date:     2018/10/25 18:34
 * Description: 导入数据库消费者
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package thread.excel;

import domain.RowProperties;
import thread.pattern.CacheQueueDataHandler;
import thread.pattern.CacheQueue;
import thread.pattern.AbstractConsumer;
import thread.pattern.Consumer;
import util.CommonUtil;

import java.util.Vector;

/**
 * 〈Coding never to stop〉<br>
 * 〈导入数据库消费者〉
 *
 * @author zombie
 * @create 2018/10/25
 * @since 1.0.0
 */
public class ImportDBConsumer extends AbstractConsumer {

    /**
     * 消息缓存队列
     */
    private  CacheQueue<RowProperties> cacheQueue;

    /**
     * 临时存放需要保存的数据,vector的capacityIncrement 增长模式为2倍增长,根据实际数据量进行预估值设置，可减少扩容所带来的时间损耗
     */
    private final Vector<RowProperties> storeDataVector = new Vector<RowProperties>(100000);

    /**
     * 消费者任务终止条件，默认为 false，当解析的excel数据为最后一条记录时，则为true
     */
    private boolean isTerminated = false;

    public ImportDBConsumer(CacheQueue<RowProperties> cacheQueue) {
        this.cacheQueue = cacheQueue;
    }


    /**
     * 消费者的任务
     */
    public void consume() {
        // 消费数据
        RowProperties rowProperties = cacheQueue.pull();

        isTerminated = rowProperties.isTerminated();
        // 处理消费者的业务逻辑
        handleConsumerData(rowProperties);
    }




    /**
     * 处理消费者数据
     * 此处主要为保存消费者的数据到数据库，可根据自己业务需求进行调整
     * 此处将消费者数据存入临时vector集合，当数据量达到默认事务提交数时，进行提交事务
     *
     * @param rowProperties 消费者消费的数据，此处为 excel 中一行数据的记录
     */
    private void handleConsumerData(RowProperties rowProperties) {
        if (rowProperties != null) {
            storeDataVector.add(rowProperties);
            if (isNeedSave(rowProperties)) {
                long start = System.currentTimeMillis();

                // 处理消费后的数据
                CacheQueueDataHandler cacheQueueDataHandler = new ExcelDataHandler();
                cacheQueueDataHandler.handlerData(storeDataVector);

                System.out.println(Thread.currentThread().getName() + "当前保存数据:" + storeDataVector.size() + "条花费时间：" + (System.currentTimeMillis() - start));
                // 重置数据容器
                storeDataVector.clear();
            }
        }
    }

    /**
     * 判断所有任务是否执行完毕
     *
     * @return
     */
    public boolean isLastTask() {
        return isTerminated;
    }

    /**
     * 判断是否需要保存
     * 若当前数据量达到需要提交的事务数或者为最后一条数据，则进行保存到数据库
     *
     * @param rowProperties 行对象
     * @return
     */
    private boolean isNeedSave(RowProperties rowProperties) {
        return size() == CommonUtil.getDefaultTranscationCommitNumber() || rowProperties.isTerminated();
    }

    /**
     * 获取当前保存数据的数据量
     *
     * @return
     */
    private int size() {
        return storeDataVector.size();
    }

}
