package Lock;


public class Main {
    public static void main(String[] args) {

        BankAccount sbi = new BankAccount();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                sbi.withdrow(100);
            }
        };

        Thread t1 = new Thread(task,"Joey");
        Thread t2 = new Thread(task,"Rachel");
        t1.start();
        t2.start();
    }
}