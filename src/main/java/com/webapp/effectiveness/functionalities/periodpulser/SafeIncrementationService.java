package com.webapp.effectiveness.functionalities.periodpulser;

import java.util.concurrent.atomic.AtomicLong;

public class SafeIncrementationService {
    private AtomicLong lifeTime = new AtomicLong();

    public Long getLifeTime( ) {
        return this.lifeTime.get();
    }

    private void incrementSecondsAlive() {
        while (true) {
            long currentLifeLength = getLifeTime();
            long next = currentLifeLength + 1;

            if (this.lifeTime.compareAndSet(currentLifeLength, next)) {
                return;
            }
        }
    }
}
