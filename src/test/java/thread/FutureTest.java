package thread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FutureTest {

    @Test
    void testFuture() throws ExecutionException, InterruptedException {
        var executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = ()->{
          Thread.sleep(5_000);
          return "Hi Rida";
        };

        Future<String> future = executor.submit(callable);
        System.out.println("Selesai future");

        while(!future.isDone())
        {
            System.out.println("waiting");
            Thread.sleep(1_000);
        }

        String value = future.get();
        System.out.println(value);
    }

    @Test
    void testFutureCancel() throws ExecutionException, InterruptedException {
        var executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = ()->{
            Thread.sleep(5_000);
            return "Hi Rida";
        };

        Future<String> future = executor.submit(callable);
        System.out.println("Selesai future");

        Thread.sleep(2_000);
        future.cancel(true);

        System.out.println(future.isCancelled());

        String value = future.get();
        System.out.println(value);
    }

    @Test
    void invokeAll() throws InterruptedException {
        var executor = Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = IntStream.range(1,11).mapToObj(value -> (Callable<String>) () ->{
            Thread.sleep(value*500);
            return String.valueOf(value);

        }).collect(Collectors.toList());

        var futures = executor.invokeAll(callables);

        futures.forEach(stringFuture -> {
            try {
                System.out.println(stringFuture.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void invokeAny() throws InterruptedException, ExecutionException {
        var executor = Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = IntStream.range(1,11).mapToObj(value -> (Callable<String>) () ->{
            Thread.sleep(value*500);
            return String.valueOf(value);

        }).collect(Collectors.toList());

        var value = executor.invokeAny(callables);

        System.out.println(value);
    }
}
