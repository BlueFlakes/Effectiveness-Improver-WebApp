package com.webapp.effectiveness.common.observator;

public class FixedSubscribersEden extends SubscribersEden {
    private int maxSize;

    public FixedSubscribersEden(int maxSize) {
        if (maxSize < 1)
            throw new IllegalArgumentException("Expect higher than 0 value.");

        this.maxSize = maxSize;
    }

    @Override
    public boolean addSubscriber(Subscriber subscriber) {
        return isCapacityExceeded() && super.addSubscriber(subscriber);
    }

    private boolean isCapacityExceeded() {
        return this.size() < this.maxSize;
    }
}
