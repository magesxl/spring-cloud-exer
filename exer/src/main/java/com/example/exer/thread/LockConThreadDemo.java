package com.example.exer.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConThreadDemo {

    private Lock lock = new ReentrantLock();

    private Condition conA = lock.newCondition();

    private Condition conB = lock.newCondition();

    private Condition conC = lock.newCondition();

    private static int count = 0;

    public static void main(String[] args) {
        LockConThreadDemo demo = new LockConThreadDemo();
        demo.conLock();
    }

    private void conLock() {

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lock.lock();
                    if (count % 3 != 0)
                        conA.await();
                    System.out.println(String.format("第%d次打印", i + 1));
                    System.out.println("A");
                    count++;
                    conB.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lock.lock();
                    if (count % 3 != 1)
                        conB.await();
                    System.out.println("B");
                    count++;
                    conC.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lock.lock();
                    if (count % 3 != 2)
                        conC.await();
                    System.out.println("C");
                    count++;
                    conA.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
