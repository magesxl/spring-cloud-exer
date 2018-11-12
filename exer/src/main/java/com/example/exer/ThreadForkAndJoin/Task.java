package com.example.exer.ThreadForkAndJoin;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {

    private static final Long serialVersionUID = 1L;

    private List<Product> list;

    //声明两个私有的int属性，分别命名为first和last。这两个属性将决定任务执行时对产品的分块
    private int first;

    private int last;

    //声明一个名为increment的私有double属性，用来存储产品价格的增加额。
    private BigDecimal increment;

    public Task(List<Product> list, int first, int last, BigDecimal increment) {
        this.list = list;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    //实现compute()方法，实现任务的执行逻辑
    //如果last和first属性值的差异小于10（一个任务只能更新少于10件产品的价格），则调用updatePrices()方法增加这些产品的价格
    @Override
    protected void compute() {
        //如果last和first属性值的差异小于10（一个任务只能更新少于10件产品的价格），则调用updatePrices()方法增加这些产品的价格
        //如果last和first属性值的差异大于或等于10，就创建两个新的Task对象，一个处理前一半的产品，另一个处理后一半的产品，
        // 然后调用ForkJoinPool的invokeAll()方法来执行这两个新的任务。
        if (last - first < 10) {
            updatePrices();
        } else {
            int middle = (first + last) / 2;
            Task t1 = new Task(list, first, middle + 1, increment);
            Task t2 = new Task(list, middle + 1, last, increment);
            invokeAll(t1, t2);
        }
    }

    private void updatePrices() {
//        list.stream().map(Product::getPrice).map((a) -> a.add(getIncrement())).collect(Collectors.toList());
        for (int i = first; i < last; i++) {
            Product product = list.get(i);
            product.setPrice(product.getPrice().add(product.getPrice().multiply(increment)));
        }
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public BigDecimal getIncrement() {
        return increment;
    }

    public void setIncrement(BigDecimal increment) {
        this.increment = increment;
    }

}

