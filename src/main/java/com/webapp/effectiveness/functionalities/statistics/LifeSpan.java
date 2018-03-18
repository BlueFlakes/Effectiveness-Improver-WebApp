package com.webapp.effectiveness.functionalities.statistics;

import com.webapp.effectiveness.common.datastructures.atomics.AtomicLongWrapper;
import com.webapp.effectiveness.common.observator.Subscriber;

public class LifeSpan implements Subscriber {
    private AtomicLongWrapper lifeSpan;

    {
        this.lifeSpan = new AtomicLongWrapper();
    }

    public long getLength() {
        return this.lifeSpan.getCurrentValue();
    }

    @Override
    public void notifySubscriber( ) {
        this.lifeSpan.increment();
    }
}
