package Volatile;

import java.util.concurrent.atomic.AtomicInteger;

//
public class VolatileCounter {
    // private volatile int count = 0;
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }

    public static void main(String[] args) {
        VolatileCounter counter = new VolatileCounter();

        Thread incrementerThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread readerThread = new Thread(() -> {
            while (counter.getCount() < 1000) {
                // Wait until the count reaches 1000
            }
            System.out.println("Final count: " + counter.getCount());
        });

        incrementerThread.start();
        readerThread.start();
    }
}
