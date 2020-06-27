package hw.les12_multithreading2;

public interface ThreadPool {

    void start();

    void execute(Runnable runnable);
}
