package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {

    @Test
    void delayedJob() throws InterruptedException {
        var executor = Executors.newScheduledThreadPool(10);

        var future = executor.schedule(()->{
            System.out.println("Hello schedule");
        }, 5, TimeUnit.SECONDS);

        System.out.println(future.getDelay(TimeUnit.SECONDS));

        executor.awaitTermination(5, TimeUnit.DAYS);
    }

    @Test
    void periodicJob() throws InterruptedException {
        var executor = Executors.newScheduledThreadPool(10);

        var future = executor.scheduleAtFixedRate(()->{
            System.out.println("Hello schedule");
        }, 5, 2, TimeUnit.SECONDS);

        System.out.println(future.getDelay(TimeUnit.SECONDS));

        executor.awaitTermination(5, TimeUnit.DAYS);
    }
}
