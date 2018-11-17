/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AbstractConsumer
 * Author:   Dell
 * Date:     2018/10/27 10:46
 * Description: 消费者抽象实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package thread.pattern;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 〈Coding never to stop〉<br>
 * 〈消费者抽象实现类〉
 *
 * @author zombie
 * @create 2018/10/27
 * @since 1.0.0
 */
public abstract class AbstractConsumer implements Consumer {

    /**
     * 判断当前任务是否为最后一个执行的任务
     * 若 true ,则表示需要停止消费，终止当前线程
     * 若 false ,则表示继续执行任务，继续消费
     * 子类必须实现该方法，用于中断生产者消费者线程
     *
     * @return
     */
    protected abstract boolean isLastTask();


    /**
     * 调用消费者时，需要执行的任务
     *
     *  此处采用设计模式中： 模板方法模式
     *  模板方法模式：定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。
     *  消费者执行流程的为固定骨架，而唯一的区别在于实现逻辑，因此我们可以采用该设计模式
     *  其中<Method>task()</Method> 为消费者执行的具体业务逻辑，由子类提供
     *  <Method>isLastTask()</Method>  为该线程模式的终止条件
     */
    public void run() {
        while (true) {
            consume();
            if (isLastTask()) {
                printLogInfoToConsole();
                // 若所有任务执行完毕，则退出当前线程
                break;
            }
        }
    }

    /**
     * 打印日志信息到控制台
     */
    private void printLogInfoToConsole() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(Thread.currentThread().getName() + "当前数据消费完毕，准备结束，日期：" + df.format(new Date()) +
                "结束时间：" + System.currentTimeMillis());
    }
}
