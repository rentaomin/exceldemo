/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestConllection
 * Author:   Dell
 * Date:     2018/11/16 10:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 〈Coding never to stop〉<br> 
 * 〈〉
 *
 * @author zombie
 * @create 2018/11/16
 * @since 1.0.0
 */
public class TestConllection {

    /**
     * 采用list的 for each 方式遍历
     */
    public  static void foreachIteratorList(){
     /*   List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);

        if (CollectionUtils.isNotEmpty(integerList)){
            for ( num : integerList){
                System.out.println(num);
            }
        }*/
        List<Object> integerList = new ArrayList<>();
        integerList.add("1");
        integerList.add("2");
        for (Object num : integerList){
            System.out.println(num);
        }
    }

    /**
     * 采用list for循环迭代器方式遍历
     */
    public  static void forIteratorList(){
/*
       List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);

        Iterator<Integer> iterator ;
        if (CollectionUtils.isNotEmpty(integerList)){
            for ( iterator = integerList.iterator();iterator.hasNext();){
                System.out.println(iterator.next());
            }
        }
        */

        List<Object> integerList = new ArrayList<>();
        integerList.add("1");
        integerList.add("2");
        Iterator<Object> iterator ;
        for ( iterator = integerList.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }
    }

    /**
     * 采用list 的 for 下标方式遍历
     */
    public  static void forIndexIteratorList(){
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);

        if (CollectionUtils.isNotEmpty(integerList)){
         for (int i = 0 ;i < integerList.size();i++){
             System.out.println(integerList.get(i));
         }
        }
    }


    public  static void iteratorAndAddEle(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        for (Integer i :list){
            System.out.println("当前元素为："+i);
            int aaa = new Random().nextInt();
            list.add(aaa);
            System.out.println("随机添加元素："+aaa);
        }
    }

    public static void main(String[] args) {
       //TestConllection.foreachIteratorList();
      //  forIteratorList();
      //  forIndexIteratorList();
       // iteratorAndAddEle();

    }

}
