package hw.les11_Multithreading;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Pull {

    public static void main(String[] args) throws FileNotFoundException, ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        CountFact counter = new CountFact();

        List<Future<Double>> futures = new ArrayList<>();

            String src = "src/hw/les11_Multithreading/text50.txt";
            Scanner in = new Scanner(new File(src));
        //int i = 0;
        while (in.hasNextLine()) {
                String myString = in.nextLine();
                int foo;
                try {
                    foo = Integer.parseInt(myString);
                }
                catch (NumberFormatException e)
                {
                    foo = 0;
                }
                int i = foo;




            final int j = i;
            futures.add(
                    CompletableFuture.supplyAsync(
                            () -> counter.fact(j),
                            threadPool
                    ));
        }

        for (Future<Double> future : futures) {
            System.out.println(future.get());
        }

        threadPool.shutdown();




/*
        String src = "src/hw/les11_Multithreading/text50.txt";
        Scanner in = new Scanner(new File(src));

        int i = 0;
        while (in.hasNextLine()) {
            String a = in.nextLine();
            int b = (int)a;
        }

        for(int i=0; i<10;i++) {
            MyThread t = new MyThread();
            t.start();
        }

        System.out.println(fact(5));

 */
    }


}

class CountFact{
    public static Double fact(int a){
        double res = 1;

        for (int i=1;i<=a;i++){
            res *= i;
        }

        return res;
    }
}

class MyThread extends Thread {
    @Override
    public void run() {


        /*for(int i=0; i<10;i++){
            System.out.println(String.format("%d - thread %s is running", i, Thread.currentThread().getName()));
        }*/
    }
}