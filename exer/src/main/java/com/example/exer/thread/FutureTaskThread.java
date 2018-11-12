package com.example.exer.thread;

import java.util.HashMap;
import java.util.concurrent.*;

public class FutureTaskThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        CopyOnWriteArraySet set = new CopyOnWriteArraySet();
        ConcurrentHashMap map = new ConcurrentHashMap();
        FutureTask<String> futureTask = new FutureTask<>(()->{
            System.out.print("你好");
            return "你好";});
        new Thread(futureTask).start();
        System.out.print(futureTask.get());
        System.out.print(f(7));
    }

    public static int f(int n){
        if(n==0)return 0;
        if(n==1)return 1;
        return f(n-1)+f(n-2);
    }
}
