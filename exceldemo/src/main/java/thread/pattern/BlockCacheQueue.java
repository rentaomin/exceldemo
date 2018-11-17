/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: BlockCacheQueue
 * Author:   Dell
 * Date:     2018/10/25 15:33
 * Description: 阻塞缓存队列
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package thread.pattern;

import thread.pattern.CacheQueue;

import java.util.concurrent.BlockingQueue;

/**
 * 〈Coding never to stop〉<br>
 * 〈阻塞缓存队列〉
 *
 * @author zombie
 * @create 2018/10/25
 * @since 1.0.0
 */
public class BlockCacheQueue<T> implements CacheQueue<T> {

    /**
     * 阻塞队列，若队列内容为空，则会自动挂起等待生产，若队列过满，则会等待消费
     * 该队列属于有界队列，最大为 Integer.MAX_VALUE
     */
    private final BlockingQueue<T> blockingQueue;

    public BlockCacheQueue(BlockingQueue<T> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    /**
     * 把元素 element 放入队列末尾
     *
     * @param element 需要添加到队列的元素
     */
    public void push(T element) {
        try {
            blockingQueue.put(element);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从队列中取出元素
     * take() 该方法从队列中消费数据，若队列没有元素，则会挂起等待
     *
     * @return 返回从当前队列中取出的第一个元素
     */
    public T pull() {
        T element = null;
        try {
            element = blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return element;
    }

    /**
     * 获取当前队列
     * @return 缓存队列
     */
    public BlockingQueue<T> getBlockingQueue() {
        return blockingQueue;
    }
}
