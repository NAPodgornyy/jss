package hw.les12_multithreading2;

import java.util.LinkedList;

public class FixedThreadPool implements ThreadPool {

    private final int numOfThread; //количество потоков
    private final ThPool[] threads;
    private final LinkedList queue; //очередь задач

    FixedThreadPool(int n){
        this.numOfThread = n;
        queue = new LinkedList();
        threads = new ThPool[numOfThread];
    }


    @Override
    public void start() {
        for (int i=0; i<numOfThread; i++) {
            threads[i] = new ThPool();
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized(queue) {
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
