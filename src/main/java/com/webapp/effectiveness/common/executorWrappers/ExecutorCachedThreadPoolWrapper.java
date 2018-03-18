package com.webapp.effectiveness.common.executorWrappers;

import com.webapp.effectiveness.common.ApplicationCloseable;
import com.webapp.effectiveness.common.datastructures.atomics.AtomicLongWrapper;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// TODO : make better mechanism for universal cachedthreadpool

public class ExecutorCachedThreadPoolWrapper implements ApplicationCloseable {
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private AtomicLongWrapper runningThreads = new AtomicLongWrapper();

    private static ExecutorCachedThreadPoolWrapper executorWrapper;

    private ExecutorCachedThreadPoolWrapper() {}

    public static ExecutorCachedThreadPoolWrapper getInstance() {
        if (executorWrapper == null) {
            synchronized (ExecutorCachedThreadPoolWrapper.class) {
                if (executorWrapper == null) {
                    executorWrapper = new ExecutorCachedThreadPoolWrapper();
                }
            }
        }

        return executorWrapper;
    }

    public void execute(Runnable runnable) {
        if (runningThreads.getCurrentValue() >= 5)
            throw new IllegalStateException("max 5 threads");

        this.executorService.execute(runnable);
    }

    @Override
    public void onClose( ) {
        this.executorService.shutdown();
    }
}
