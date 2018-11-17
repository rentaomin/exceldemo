/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AbstractProducer
 * Author:   Dell
 * Date:     2018/10/27 9:51
 * Description: 生产者的抽象实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package thread.pattern;

/**
 * 〈Coding never to stop〉<br>
 * 〈生产者的抽象实现类〉
 *
 * @author zombie
 * @create 2018/10/27
 * @since 1.0.0
 */
public abstract class AbstractProducer implements Producer {

    /**
     * 调用生产者时，执行生产工作
     *
     *  采用模板模式 - 子类只需实现具体的生产任务，不需要关心执行顺序和逻辑
     */
    public void run() {
        while (true) {
            produce();
            break;
        }
    }



}
