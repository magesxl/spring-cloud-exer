package com.example.exer.ThreadForkAndJoin;







import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> list = generator.generate(100);
        //创建一个新的Task对象用来更新列表中的所有产品。参数first为0，参数last为产品列表的大小，即10,0
        Task task = new Task(list,0,list.size(), BigDecimal.ONE);
        //通过无参的类构造器创建一个ForkJoinPool对象。
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        do {
            System.out.println(pool.getActiveThreadCount());
            System.out.println(pool.getRunningThreadCount());
            System.out.println(pool.getStealCount());
            System.out.println(pool.getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(!task.isDone());

        pool.shutdown();

        //调用isCompletedNormally()方法，检查任务是否已经完成并且没有错误
        if(task.isCompletedNormally()){
            System.out.println("DONE");
        }
        list.stream().map(Product::getPrice).forEach(System.out::println);

    }

}
