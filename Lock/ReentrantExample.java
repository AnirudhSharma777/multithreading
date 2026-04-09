package Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    
    private final Lock lock = new ReentrantLock();

    public void methodA() {
        lock.lock();
        try {
            System.out.println("Method A is executing.");
            methodB(); // Reentrant call
        } finally {
            lock.unlock();
        }
    }

    public void methodB() {
        lock.lock();
        try {
            System.out.println("Method B is executing.");
        } finally {
            lock.unlock();
        }
    }
    

    public static void main(String[] args) {
        ReentrantExample example = new ReentrantExample();
        example.methodA();
    }
}
