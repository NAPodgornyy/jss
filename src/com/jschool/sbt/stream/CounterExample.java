package com.jschool.sbt.stream;

//counter счетчик

class Counter{
    int count;
    public synchronized void incr(){
        count++;
    }
}

public class CounterExample {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<5; i++){
                    c.incr();
                }
            }
        });

        Counter c2 = new Counter();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<5; i++){
                    c2.incr();
                }
            }
        });


        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.printf(String.valueOf(c.count));

    }
}
