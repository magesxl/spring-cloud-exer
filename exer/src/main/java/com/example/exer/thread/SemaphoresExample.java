package com.example.exer.thread;

import java.util.concurrent.Semaphore;

public class SemaphoresExample {

    public static void main(String[] args) {
        lock2();
    }

    public static void lock2() {
        // 以A开始的信号量,初始信号量数量为1
        Semaphore s1 = new Semaphore(1);
        // B、C信号量,A完成后开始,初始信号数量为0
        Semaphore s2 = new Semaphore(0);
        Semaphore s3 = new Semaphore(0);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; ) {
                try {
                    s1.acquire();
                    i++;
                    System.out.println(String.format("第%d遍", i));
                    System.out.println("A");
                    // B释放信号，B信号量为1，可以执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    s2.release();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; ) {
                try {
                    s2.acquire();
                    i++;
                    System.out.println("B");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    s3.release();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10; ) {
                try {
                    s3.acquire();
                    i++;
                    System.out.println("C");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    s1.release();
                }
            }
        });
        // 开始只有A可以获取, BC都不可以获取, 保证了A最先执行
        t1.start();
        t2.start();
        t3.start();
    }
}
