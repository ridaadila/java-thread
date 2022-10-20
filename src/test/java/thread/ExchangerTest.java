package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {

    @Test
    void exchanger() throws InterruptedException {
        final var exchanger = new Exchanger<String>();
        final var executor = Executors.newFixedThreadPool(10);

        executor.execute(()->{
            String result = null;
            try {
                result = exchanger.exchange("First");
                System.out.println("1. "+result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executor.execute(()->{
            String result = null;
            try {
                result = exchanger.exchange("Second");
                System.out.println("2. " + result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
