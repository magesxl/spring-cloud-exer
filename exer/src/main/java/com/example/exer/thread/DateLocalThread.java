package com.example.exer.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间转换线程安全方法   ThreadLocal与java8中的LocalDateTime类两种解决方法
 */
public class DateLocalThread {

    private static final Logger logger = LoggerFactory.getLogger(DateLocalThread.class);

    static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static void main(String[] args) {
        //       syncFormatDate();

//        syncLocalFormatDate();
        syncjava8();
    }


    private static void syncFormatDate() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                synchronized (sf) {
                    try {
                        System.out.println(sf.parse("2017-12-13 15:17:27"));
                    } catch (ParseException e) {
                        logger.info("解析出错");
                    }
                }
            });
            t.start();
        }
        long end = System.currentTimeMillis();
        logger.info("消耗时间为{}", end - start);
    }

    private static void syncLocalFormatDate() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(local.get().parse("2017-12-13 15:17:27"));
                } catch (ParseException e) {
                    logger.info("解析出错");
                }
            }).start();
        }
    }

    private static void syncjava8() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(LocalDateTime.parse("2017-12-13 15:17:27", formatter));
            }).start();
        }
    }
}
