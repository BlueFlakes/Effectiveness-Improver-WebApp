package com.webapp.effectiveness.functionalities.timebeater;

import com.webapp.effectiveness.common.ApplicationCloseable;
import com.webapp.effectiveness.common.cacheutils.Cache;
import com.webapp.effectiveness.common.observator.Subscriber;
import com.webapp.effectiveness.common.observator.SubscribersEden;
import com.webapp.effectiveness.common.validators.ValidatorUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

// Recommended is to test performance relation between amount of subscribers and sleeping period
// If TimeBeater get too much subscribers with very low sleeping period
// then he can fail to update every value and lose precision
public class ElapsedTimeBeater implements ApplicationCloseable {
    private static final Cache<Long, ElapsedTimeBeater> elapsedTimeBeaterCache = new Cache<>(100);
    private final ScheduledExecutorService timeBeat = Executors.newSingleThreadScheduledExecutor();

    private final SubscribersEden subscribersEden;
    private final long sleepingPeriodInMillis;

    private ElapsedTimeBeater(long sleepingPeriodInMillis,
                              Supplier<SubscribersEden> edenSupplier) {

        if (sleepingPeriodInMillis < 1)
            throw new IllegalArgumentException("Invalid period. Expect greater than 0");

        this.sleepingPeriodInMillis = sleepingPeriodInMillis;
        this.subscribersEden = edenSupplier.get();
        startTimeBeater();
    }

    private void startTimeBeater() {
        ElapsedTimeBeaterExecutor elapsedTimeBeaterExecutor = new ElapsedTimeBeaterExecutor();
        timeBeat.scheduleAtFixedRate(elapsedTimeBeaterExecutor, 0, sleepingPeriodInMillis, TimeUnit.MILLISECONDS);
    }

    public static ElapsedTimeBeater createCached(long sleepingPeriodInMillis,
                                                 Supplier<SubscribersEden> edenSupplier) {

        ValidatorUtils.requireNonNull(edenSupplier);
        return elapsedTimeBeaterCache.create(sleepingPeriodInMillis,
                                        () -> new ElapsedTimeBeater(sleepingPeriodInMillis, edenSupplier));
    }

    public static ElapsedTimeBeater create(long sleepingPeriodInMillis,
                                           Supplier<SubscribersEden> edenSupplier) {

        ValidatorUtils.requireNonNull(edenSupplier);
        return new ElapsedTimeBeater(sleepingPeriodInMillis, edenSupplier);
    }

    public boolean addSubscriber(Subscriber subscriber) {
        return this.subscribersEden.addSubscriber(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        this.subscribersEden.removeSubscriber(subscriber);
    }

    public long getSleepingPeriodInMillis( ) {
        return sleepingPeriodInMillis;
    }

    private class ElapsedTimeBeaterExecutor implements Runnable {
        @Override
        public void run( ) {
            subscribersEden.notifySubscribers();
        }
    }

    @Override
    public void onClose( ) {
        this.subscribersEden.onClose();
        this.timeBeat.shutdownNow();
    }
}
