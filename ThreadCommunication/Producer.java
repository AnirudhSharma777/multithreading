package ThreadCommunication;

public class Producer implements Runnable{

    private SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        for(int i = 0; i<10; i++) {
            try {
                resource.produce(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
}
