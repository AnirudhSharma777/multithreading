package Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCounter {
    
    private int count = 0;

    private final  ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment() {
        writeLock.lock();
        try {
            count++;
        } finally {
            writeLock.unlock();
        }
    }

    public int getCount() {
        readLock.lock();
        try {
            return count;
        }finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockCounter counter = new ReadWriteLockCounter();


        Runnable writer = () -> {
            for(int i = 0; i<10; i++) {
                counter.increment();
                System.out.println(Thread.currentThread().getName() + " incremented count");
            }
        };

        Runnable reader = () ->  {
            for(int i = 0; i<10; i++) {
                System.out.println(Thread.currentThread().getName() + " read count: " + counter.getCount());
            }
        };

        Thread writerThread = new Thread(writer);
        Thread readerThread1 = new Thread(reader);
        Thread readerThread2 = new Thread(reader);

        writerThread.start();
        readerThread1.start();
        readerThread2.start();

        try {
            writerThread.join();
            readerThread1.join();
            readerThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
