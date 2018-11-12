package com.example.exer.common;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lamdba1 {

    public static void main(String[] args) {
        List<Person> list = getIntList();
        list.stream().filter(p -> p.equals("lki")).forEach(System.out::println);
        list.stream().filter(p -> p.getAge() > 2).forEach(it -> System.out.println(it.getAge()));
        Collections.sort(list, Comparator.comparing(Person::getAge));
        //排序
        Collections.sort(list,Comparator.comparing(Person::getName).thenComparing(Person::getAge));
        list.stream().map(Person::getAge).map((a)->a*a).forEach(System.out::println);

        //stream过滤对象
        list.stream().filter((t)->t.getAge()>2).mapToInt(Person::getAge).sum();
        int sum = list.stream().mapToInt(Person::getAge).sum();
        System.out.println(sum);
        List<String> list1 = getStringList();
        Iterator it = list.iterator();
        while(it.hasNext()){

        }

    }

    public static List<String> getStringList() {
        List<String> list = new ArrayList<>();
        list.add("sdddd");
        list.add("lki");
        list.add("opliij");
        return list;
    }


    public static List<Person> getIntList() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("zhangsan",1,"男"));
        list.add(new Person("lisi",2,"男"));
        list.add(new Person("niujyu",3,"nv"));
        return list;
    }
}
