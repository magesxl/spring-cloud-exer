package com.example.exer.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用sleep
 * 使用synchronized, wait和notifyAll
 * 使用Lock 和 Condition
 * 使用Semaphore
 * 使用AtomicInteger
 */
public class LockThreadDemo {

    private Lock lock = new ReentrantLock();

    private static int status = 0;

    public static void main(String[] args) {
        LockThreadDemo demo = new LockThreadDemo();
        demo.printA();
    }


    private void printA() {
        new Thread(() -> {
            for (int i = 0; i < 10; ) {
                lock.lock();
                if (status % 3 == 0) {
                    System.out.println(String.format("第%d次打印", i + 1));
                    System.out.println("A");
                    status++;
                    i++;
                }
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; ) {
                lock.lock();
                if (status % 3 == 1) {
                    System.out.println("B");
                    status++;
                    i++;
                }
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; ) {
                lock.lock();
                if (status % 3 == 2) {
                    System.out.println("C");
                    status++;
                    i++;
                }
                lock.unlock();
            }
        }).start();
    }
}
