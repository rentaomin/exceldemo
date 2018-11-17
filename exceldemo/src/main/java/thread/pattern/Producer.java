/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: producer
 * Author:   Dell
 * Date:     2018/10/25 17:46
 * Description: 生产者模式
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package thread.pattern;

/**
 * 〈Coding never to stop〉<br>
 * 〈生产者模式〉
 * 提供生产者接口
 *
 * @author zombie
 * @create 2018/10/25
 * @since 1.0.0
 */
public interface Producer<T> extends Runnable {

    /**
     * 生产者生产对象
     */
    void produce();
}
