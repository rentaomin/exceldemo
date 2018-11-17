/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CacheQueueDataHandler
 * Author:   zombie
 * Date:     2018/10/29 14:43
 * Description: 缓存队列中的数据处理接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package thread.pattern;

import java.util.Vector;

/**
 * 〈Coding never to stop〉<br>
 * 〈缓存队列中的数据处理接口〉
 *
 * @author zombie
 * @create 2018/10/29
 * @since 1.0.0
 */
public interface CacheQueueDataHandler {

    /**
     * 对缓存队列中取出的元素进行业务处理
     *
     * @param vector 缓存队列中取出的元素集合
     */
    void handlerData(Vector vector);
}
