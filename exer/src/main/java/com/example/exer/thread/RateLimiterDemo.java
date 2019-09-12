package com.example.exer.thread;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 限速率  可以与多线程中的信号量
 */
public class RateLimiterDemo {
    public static void main(String... args) {
        limit();
    }

    public static void limit() {
        RateLimiter limiter = RateLimiter.create(2.0);
        System.out.println(limiter.acquire(5));
        System.out.println(limiter.acquire(4));
    }
}
