package com.example.exer.thread;

public class SleepThreadDemo {

    public static void main(String[] args) {
        sleep();
    }

    private static int currentCount = 0;

    private String name = "A";


    public SleepThreadDemo(String name) {
        this.setName(name);
    }

    private static void increase() {
        currentCount++;
    }

    public static void sleep() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (currentCount % 3 == 0) {
                    System.out.println("A");
                    increase();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (currentCount % 3 == 1) {
                    System.out.println("B");
                    increase();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (currentCount % 3 == 2) {
                    System.out.println("C");
                    increase();
                }
            }
        }).start();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
