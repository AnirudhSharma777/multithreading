package ThreadCommunication;

public class SharedResource {
    private int value;
    private boolean hasData = false;

    public synchronized void produce(int newValue) throws InterruptedException {
        while(hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.value = newValue;
        System.out.println("Produced: " + this.value);
        this.hasData = true;
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while(!hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        int consumedValue = this.value;
        System.out.println("Consumed: " + consumedValue);
        this.hasData = false;
        notify();
        return consumedValue;
    }
}
