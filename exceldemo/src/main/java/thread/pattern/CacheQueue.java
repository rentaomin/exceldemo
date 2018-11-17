/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CacheQueue
 * Author:   zombie
 * Date:     2018/10/25 15:10
 * Description: 缓存队列接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package thread.pattern;

import java.util.Queue;

/**
 * 〈Coding never to stop〉<br>
 * 〈缓存队列接口〉
 * <p>
 * 目的：为生产者和消费者提供一个中间件缓存队列，生产者生产数据放入 CacheQueue队列，
 * 消费者从该 CacheQueue 队列取数据，从而达到生产者和消费者解耦，并行执行，从而
 * 提高执行效率和响应时间
 *
 * @author zombie
 * @Method produce(T t) 用于生产者生产数据放入缓存队列CacheQueue，
 * @Method T consume()  消费者从该队列CacheQueue消费数据
 * @create 2018/10/25
 * @since 1.0.0
 */
public interface CacheQueue<T> {

    /**
     * 把元素添加到队列
     */
    void push(T t);

    /**
     * 从队列中取出元素
     *
     * @return 队列中取出的元素
     */
    T pull();

}
