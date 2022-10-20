package thread;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentMapTest {

    @Test
    void test() throws InterruptedException {
        final var map = new ConcurrentHashMap<Integer, String>();
        final var executor = Executors.newFixedThreadPool(100);
        final var countDown = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(1_000);
                    map.putIfAbsent(index, "Data-" + index);
                    System.out.println("put to Map from thread: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDown.countDown();
                }
            });
        }

        executor.execute(() -> {
            try {
                countDown.await();
                map.forEach((integer, string)-> System.out.println(integer + ":" + string +", from thread: " + Thread.currentThread().getName()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void testCollection()
    {
        List<String> list = List.of("rida", "adila", "test");
        List<String> synchronizedList = Collections.synchronizedList(list);
    }
}
