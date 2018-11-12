package com.example.exer.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    private static AtomicInteger atomic = new AtomicInteger(0);

    private static final int MAX_CYC_COUNT = 30;

    public static void main(String[] args) {
        atomicPrint();
    }

    public static void atomicPrint() {
        new Thread(() -> {
            while (atomic.get() < 30) {
                if (atomic.get() % 3 == 0) {
                    System.out.println(String.format("第%d次打印", atomic.get() / 3 + 1));
                    System.out.println("A");
                    atomic.incrementAndGet();
                }
            }
        }).start();

        new Thread(() -> {
            while (atomic.get() < 30) {
                if (atomic.get() % 3 == 1) {
                    System.out.println("B");
                    atomic.incrementAndGet();
                }
            }
        }).start();

        new Thread(() -> {
            while (atomic.get() < 30) {
                if (atomic.get() % 3 == 2) {
                    System.out.println("C");
                    atomic.incrementAndGet();
                }
            }
        }).start();
    }
}
