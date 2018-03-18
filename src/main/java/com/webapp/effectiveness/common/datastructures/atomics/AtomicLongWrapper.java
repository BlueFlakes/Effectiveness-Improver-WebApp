package com.webapp.effectiveness.common.datastructures.atomics;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongWrapper {
    private AtomicLong atomicLong = new AtomicLong();

    public long getCurrentValue( ) {
        return this.atomicLong.get();
    }

    public void increment() {
        while (true) {
            long currentLifeLength = getCurrentValue();
            long next = currentLifeLength + 1;

            if (this.atomicLong.compareAndSet(currentLifeLength, next)) {
                return;
            }
        }
    }
}
