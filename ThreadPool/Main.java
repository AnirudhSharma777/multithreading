package ThreadPool;

public class Main {
    public static void main(String[] args) {
        Thread[] threads = new Thread[9];

        for(int i = 1; i<10; i++) {
            final int index = i;
            threads[i-1] = new Thread(() -> {
                long result = factorial(index);
                System.out.println("Thread " + index + " is running.");
                System.out.println("Factorial of " + index + " is: " + result);
            });
        }
    }

    private static long factorial(int n) {
        if(n == 0) return 1;
        return n * factorial(n - 1);
    }
}
