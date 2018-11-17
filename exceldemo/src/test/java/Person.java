/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Person
 * Author:   Dell
 * Date:     2018/11/16 10:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import java.util.*;

/**
 * 〈Coding never to stop〉<br> 
 * 〈〉
 *
 * @author zombie
 * @create 2018/11/16
 * @since 1.0.0
 */
public class Person implements Iterable<Person>{

    private String name;

    private int age;

    private List<Person> childrenList = new ArrayList<>();

    public Person(String name, int age, Person ... kids) {
        this.name = name;
        this.age = age;
        for (Person child:kids){
            this.childrenList.add(child);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", childrenList=" + childrenList +
                '}';
    }

    @Override
    public Iterator<Person> iterator() {
        return this.childrenList.iterator();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Person> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Person> childrenList) {
        this.childrenList = childrenList;
    }

    public static void main(String[] args) {
        Person person = new Person("大头爸爸",38,
                            new Person("小头儿子1" ,8),
                            new Person("小头儿子2",9));
        System.out.println(person.toString());
/*
        for (Person kid :person){
            System.out.println(kid.getName());
        }*/
    List<Person>  kids = new ArrayList<Person>(person.getChildrenList());

        Collections.reverse(kids);
        System.out.println(kids);
    }
}
