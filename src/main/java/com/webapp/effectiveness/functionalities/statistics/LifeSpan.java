package com.webapp.effectiveness.functionalities.statistics;

import com.webapp.effectiveness.common.datastructures.atomics.ConcurrentLong;
import com.webapp.effectiveness.common.observator.Subscriber;

public class LifeSpan implements Subscriber {
    private ConcurrentLong lifeSpan;

    {
        this.lifeSpan = new ConcurrentLong();
    }

    public long getLength() {
        return this.lifeSpan.getCurrentValue();
    }

    @Override
    public void notifySubscriber( ) {
        this.lifeSpan.increment();
    }
}
