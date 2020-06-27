package hw.les12_multithreading2;

import java.util.LinkedList;

import static java.lang.Thread.State.RUNNABLE;
import static java.lang.Thread.State.WAITING;

public class ScalableThreadPool implements ThreadPool {

    private volatile static int numOfThread;
    private final int minNumOfThread; //количество потоков
    private final int maxNumOfThread; //количество потоков
    private final ThPool[] threads; //массив потоков
    private final LinkedList queue; //очередь задач

    ScalableThreadPool(int n, int m){
        this.minNumOfThread = n;
        this.maxNumOfThread = m;
        queue = new LinkedList();
        threads = new ThPool[maxNumOfThread];
    }


    @Override
    public void start() {
        for (int i=0; i<minNumOfThread; i++) {
            threads[i] = new ThPool();
            threads[i].start();
        }
        numOfThread = minNumOfThread;
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized(queue) {
            int k = 0;
            //проверяем заняты ли все потоки
            for (int i=0; i<numOfThread; i++) {
                //если поток не находится в статусе ожидания и не выполняются? хз, можно оставить что-то одно
                if(threads[i].getState() != WAITING && threads[i].getState() != RUNNABLE){
                    k++;
                }
            }
            //если все потоки заняты и не привышено максимальное число потоков
            if(k==numOfThread && numOfThread<maxNumOfThread){
                threads[numOfThread] = new ThPool(); //добавляем поток
                threads[numOfThread].start();
                numOfThread++;
            }
            queue.addLast(runnable);
            queue.notify();
        }
    }

    private class ThPool extends Thread {

        public void run() {
            Runnable runnable;

            while (true) { //поток работает всегда, чтоб принимать новые задания
                synchronized(queue) { //синхроним очередь, чтоб видеть новые задания
                    while (queue.isEmpty()) { //если очередь пуста кидаем поток в ожидание, из ожидание выходим при добавлении нового задания с помощью queue.notify();
                        try
                        {
                            queue.wait(); //поток в ожидании
                        }
                        catch (InterruptedException ignored)
                        {
                        }
                    }

                    //здесь выполняем операции над очередью

                    runnable = (Runnable) queue.removeFirst(); //удаляем первый елемент из очереди
                }

                // Если мы не поймаем RuntimeException,
                // пул может пропускать потоки
                try {
                    runnable.run(); //запускаем поток по новой, и выполняем следующие задания
                }
                catch (RuntimeException e) {
                    // добавить обработку
                }
            }
        }
    }
}
