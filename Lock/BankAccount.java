package Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private double BALANCE = 1000.00;

    private final Lock lock = new ReentrantLock();

    public void withdrow(double amount){
        System.out.println(Thread.currentThread().getName() + " is trying to withdrow");
        try{
            if(lock.tryLock(1000,TimeUnit.MILLISECONDS)){
                System.out.println(Thread.currentThread().getName() + " acquired the lock");
                if(BALANCE >= amount){
                    try {
                        System.out.println(Thread.currentThread().getName() + " is withdrowing " + amount);
                        Thread.sleep(3000);
                        BALANCE-= amount;
                        System.out.println(Thread.currentThread().getName() + " has withdrowed " + amount+ " and remaining balance is " + (BALANCE));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }finally {
                        lock.unlock();
                    }
                }else{
                    System.out.println(Thread.currentThread().getName() + " is trying to withdrow but insufficient balance");
                }
                
            }else{
                System.out.println(Thread.currentThread().getName() + " is trying to acquire the lock but it is not available");
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
