package com.webapp.effectiveness.common.observator;

public class FixedSubscribersEden extends SubscribersEden {
    private int maxSize;

    public FixedSubscribersEden(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean addSubscriber(Subscriber subscriber) {
        return !isCapacityExceeded() && super.addSubscriber(subscriber);
    }

    private boolean isCapacityExceeded() {
        return this.size() > this.maxSize;
    }
}
