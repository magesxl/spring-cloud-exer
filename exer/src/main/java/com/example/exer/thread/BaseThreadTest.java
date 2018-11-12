package com.example.exer.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseThreadTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Thread t1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + ":A"));
        t1.start();
        //创建线程三种方法   继承Thread、实现Runnable、实现Callable
//        通过Callable和Future创建线程
//
//        创建Callable接口的实现类，并实现call()方法，该call()方法将作为线程执行体，并且有返回值；
//        创建Callable实现类的实例，使用FutureTask类来包装Callable对象，该FutureTask对象封装了该Callable对象的call()方法的返回值；
//        使用FutureTask对象作为Thread对象的target创建并启动新线程；
//        调用FutureTask对象的get()方法来获得子线程执行结束后的返回值，注意不是直接调用Callable对象的call()方法获取返回值，Callable对象的call()方法为线程的执行体被调用；

//        Future接口方法
//        V get() : 返回Callable对象里call()方法的返回值。调用该方法将导致程序阻塞，必须等待子线程结束后才回得到返回值；
//        V get(long timeout, TimeUnit unit) : 返回Callable对象里call()方法的返回值。调用该方法将导致程序最多阻塞timeout和unit指定时间，如果经过指定时间后，Callable任务依然没有返回值，则抛出TimeoutException异常；
//        boolean cancel(boolean mayInterruptIfRunning) : 试图取消Future里关联的Callable任务；
//        boolean isCancelled() : 如果在Callable任务正常完成前被取消，返回true；
//        boolean isDone() : 如果Callable任务已完成，返回true；

        FutureTask<Integer> f1 = new FutureTask(new Callable() {
            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                return 1;
            }
        });



        FutureTask<Integer> f2 = new FutureTask<Integer>(()->{return 2;});
//        Thread t2 = new Thread(f1);
//
//        t2.start();
//        System.out.println(f1.get());

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(f1);
        executor.submit(f2);
        System.out.println(f1.get());
        System.out.println(f2.get());
        executor.shutdown();

//        新建状态（NEW）
//        可运行状态（RUNNABLE）
//        阻塞状态（BLOCKED）
//        等待状态（WAITING）
//        计时等待状态（TIMED_WAITING）
//        终止状态（TERMINATED）

        AtomicInteger ai = new AtomicInteger(0);
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
        System.out.println(ai.incrementAndGet());
        System.out.println(ai.get());

    }
}
