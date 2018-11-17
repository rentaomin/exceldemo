package thread.channel;

import org.junit.Test;
import thread.pattern.BlockCacheQueue;

import java.util.concurrent.ArrayBlockingQueue;

import static org.junit.Assert.*;

public class BlockCacheQueueTest {

    /**
     * 若设置队列大小，放入元素大于队列大小时，则会阻塞
     */
    @Test
    public void push() {
            // 测试阻塞队列
        //BlockCacheQueue blockCacheQueue = new BlockCacheQueue(new LinkedBlockingQueue());
        // 测试切换其它队列
        BlockCacheQueue blockCacheQueue = new BlockCacheQueue(new ArrayBlockingQueue(10));
        for (int i = 0 ; i< 3;i++){
            blockCacheQueue.push("aa"+i);
        }
        int size = blockCacheQueue.getBlockingQueue().size();
        assertEquals(3,size);
        String firstELe = (String)blockCacheQueue.getBlockingQueue().peek();
        // 判断队列第一个元素是否为先放进去的元素
        assertEquals("aa0",firstELe);

    }

    @Test
    public void pull() {

        // 测试切换其它队列
        BlockCacheQueue blockCacheQueue = new BlockCacheQueue(new ArrayBlockingQueue(10));
        // 测试若队列size小于放入队列的元素，则队列会阻塞
      // BlockCacheQueue blockCacheQueue = new BlockCacheQueue(new LinkedBlockingQueue(2));
        // 队列默认不设置size，则不会有影响
        //BlockCacheQueue blockCacheQueue = new BlockCacheQueue(new LinkedBlockingQueue());
        for (int i = 0 ; i< 3;i++){
            blockCacheQueue.push("aa"+i);
        }
        int size = blockCacheQueue.getBlockingQueue().size();
        String  lastEle = (String) blockCacheQueue.pull();
        // 判断队列是否为先进先出，取出的元素是否为放入的第一个元素
        assertEquals("aa0",lastEle);
        // 判断是否能够成功消费队列中的元素
        assertEquals(size-1,2);
    }
}