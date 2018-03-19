package com.webapp.effectiveness.common.datastructures.atomics;

import java.util.concurrent.atomic.LongAdder;

public class ConcurrentLong {
    private LongAdder longAdder = new LongAdder();

    public long getCurrentValue( ) {
        return this.longAdder.longValue();
    }

    public void increment() {
        this.longAdder.increment();
    }

    public void restart() {
        this.longAdder.reset();
    }
}
