package com.webapp.effectiveness.functionalities.periodpulser;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongWrapper {
    private AtomicLong lifeTime = new AtomicLong();

    public Long getLifeTime( ) {
        return this.lifeTime.get();
    }

    public void increment() {
        while (true) {
            long currentLifeLength = getLifeTime();
            long next = currentLifeLength + 1;

            if (this.lifeTime.compareAndSet(currentLifeLength, next)) {
                return;
            }
        }
    }
}
