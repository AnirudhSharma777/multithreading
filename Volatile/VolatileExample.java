package Volatile;


// In this example, we have a SharedObject class with a boolean flag. The writer thread sets the flag to true after a short delay,
//  while the reader thread continuously checks the flag until it becomes true. 
// By declaring the flag as volatile, we ensure that the reader thread sees the 
// updated value set by the writer thread, demonstrating the visibility guarantee provided by the volatile keyword.



class SharedObject {
    private volatile boolean flag = false;

    public void setFlagTrue() {
        System.out.println("Setting flag to true...");
        this.flag = true;
    }

    public void printFlagTrue() {
        while(!flag) {
            // do nothing
        }
        System.out.println("Flag is now true.");
    }

}
public class VolatileExample {
    
    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject();

        Thread writerThread = new Thread(() -> {
            try {
                Thread.sleep(1000); // Simulate some work
                sharedObject.setFlagTrue();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });
        
        Thread readerThread = new Thread(() -> {
            sharedObject.printFlagTrue();
        });
        writerThread.start();
        readerThread.start();
    }
}
