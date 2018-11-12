package com.example.exer.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaExer {
    public static void main(String[] args) {
        //lambda表达式 ()->{}取代匿名内部类
        //通常都会把lambda表达式内部变量的名字起得短一些。这样能使代码更简短，放在同一行。所以，在上述代码中，变量名选用a、b或者x、y会比even、odd要好。
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("你好1");
            }
        });


        Thread t2 = new Thread(() -> System.out.println("你好"));

        t1.start();
        t2.start();


        List<String> list  = new ArrayList<>();
        list.add("sdddd");
        list.add("lki");
        list.add("opliij");
        list.forEach(System.out::println);
        Collections.sort(list, (a,b)->a.compareTo(b));
 //       list.sort((String a, String b)-> b.compareTo(a));
        list.forEach(System.out::println);
        List<String> list1 = list.parallelStream().filter((name)->name.startsWith("s")).collect(Collectors.toList());
        System.out.println(list1);

    }

    public static void exer() {

    }
}
