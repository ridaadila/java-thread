package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

    @Test
    void testExecutorService() throws InterruptedException {
        var executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100; i++) {
            executor.execute(()->{
                try {
                    Thread.sleep(1_000);
                    System.out.println("runnable in thread: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void testExecutorServiceFixed() throws InterruptedException {
        var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 100; i++) {
            executor.execute(()->{
                try {
                    Thread.sleep(1_000);
                    System.out.println("runnable in thread: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
