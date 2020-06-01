package core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadHelper {
    private final ExecutorService mExecutorService = Executors.newFixedThreadPool(1);

    public ThreadHelper() {
    }

    public void sendTask(Runnable runnable) {
        try {
            mExecutorService.submit(runnable);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
