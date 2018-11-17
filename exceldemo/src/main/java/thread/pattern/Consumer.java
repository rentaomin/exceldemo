/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Consumer
 * Author:   Dell
 * Date:     2018/10/25 17:47
 * Description: 消费者模式
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package thread.pattern;

/**
 * 〈Coding never to stop〉<br>
 * 〈消费者模式〉
 * <p>
 * 提供消费者接口
 *
 * @author zombie
 * @create 2018/10/25
 * @since 1.0.0
 */
public interface Consumer extends Runnable {


    /**
     * 消费者具体执行的任务，子类需重写该方法实现自己的业务逻辑
     */
    void consume();

}
