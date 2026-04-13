package ThreadPool.ExecutorFramework;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ExecutorFramework {


        public static void main(String[] args) {
        // long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for(int i = 1; i<10; i++) {
            int index = i;
            Future<?> future =  executor.submit(() -> {
                long result = factorial(index);
                // System.out.println("Thread "+ i/ + " is running.");
                System.out.println("Factorial of " + index + " is: " + result);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    
                    e.printStackTrace();
                }
                
            });
        }

        CompletableFuture<?> future = CompletableFuture.supplyAsync(() -> {
            long result = factorial(10);
            System.out.println("Factorial of 10 is: " + result);
            return result;
        }, executor);

        executor.shutdown();
        // try {
        //     executor.awaitTermination(100,TimeUnit.SECONDS);
        // } catch (InterruptedException e) {
        //     Thread.currentThread().interrupt();
        // }
        // System.out.println("All tasks submitted." + (System.currentTimeMillis() - startTime) + "ms");

    }


    private static long factorial(int n) {
        if(n == 0) return 1;
        return n * factorial(n-1);
    }
}
