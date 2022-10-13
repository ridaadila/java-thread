package thread;

import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    void mainThread()
    {
        var name = Thread.currentThread().getName();
        System.out.println(name);
    }

    @Test
    void createThread()
    {
        Runnable runnable = ()-> {
            System.out.println("Hello from thread: " + Thread.currentThread().getName());
        };

        var thread = new Thread(runnable);
        thread.start();

        System.out.println("program telah selesai.");
    }

    @Test
    void threadSleep() throws InterruptedException {
        Runnable runnable = ()-> {
            try{
                Thread.sleep(2_000);
                System.out.println("Hello from thread: " + Thread.currentThread().getName());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        var thread = new Thread(runnable);
        thread.start();

        thread.sleep(3_000);
        System.out.println("program telah selesai.");
    }

    @Test
    void threadJoin() throws InterruptedException {
        Runnable runnable = ()-> {
            try{
                Thread.sleep(2_000);
                System.out.println("Hello from thread: " + Thread.currentThread().getName());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        var thread = new Thread(runnable);
        thread.start();

        System.out.println("menunggu selesai.");
        thread.join();
        System.out.println("program telah selesai.");
    }

    @Test
    void threadInterrupt() throws InterruptedException {
        Runnable runnable = ()-> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Hello from runnable: " + i);
                try{
                    Thread.sleep(1_000);
                }
                catch (InterruptedException e) {
                    return;
                }
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000);
        thread.interrupt();
        System.out.println("menunggu selesai.");
        thread.join();
        System.out.println("program telah selesai.");
    }

    @Test
    void threadName()
    {
        var thread = new Thread(()->{
            System.out.println("Run in thread: " + Thread.currentThread().getName());
        });
        thread.setName("Rida");
        thread.start();
    }

    @Test
    void threadState() throws InterruptedException {
        var thread = new Thread(()->{
            System.out.println(Thread.currentThread().getState());
            System.out.println("Run in thread: " + Thread.currentThread().getName());
        });
        thread.setName("Rida");
        System.out.println(thread.getState());
        thread.start();
        thread.join();
        System.out.println(thread.getState());
    }
}
