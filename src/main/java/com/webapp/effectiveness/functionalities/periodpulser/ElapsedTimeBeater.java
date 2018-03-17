package com.webapp.effectiveness.functionalities.periodpulser;

import com.webapp.effectiveness.common.observator.SubscribersEden;


public class ElapsedTimeBeater implements Runnable {
    public final SubscribersEden subscribersEden = new SubscribersEden();
    private final long sleepingPeriodInMillis;

    public ElapsedTimeBeater(long sleepingPeriodInMillis) {
        if (sleepingPeriodInMillis < 1)
            throw new IllegalArgumentException("Invalid period. Expect greater than 0");

        this.sleepingPeriodInMillis = sleepingPeriodInMillis;
    }

    public void run( ) {
        while (true) {
            try {
                Thread.sleep(this.sleepingPeriodInMillis);
                this.subscribersEden.notifySubscribers();

            } catch (InterruptedException e) {
                this.subscribersEden.onClose();
                return;
            }
        }
    }
}
