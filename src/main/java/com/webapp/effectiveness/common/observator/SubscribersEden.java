package com.webapp.effectiveness.common.observator;

import com.webapp.effectiveness.common.ApplicationCloseable;
import com.webapp.effectiveness.common.validators.ValidatorUtils;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubscribersEden implements ApplicationCloseable {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Set<Subscriber> subscribers;

    {
        this.subscribers = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public boolean addSubscriber(Subscriber subscriber) {
        ValidatorUtils.requireNonNull(subscriber);
        this.subscribers.add(subscriber);

        return true;
    }

    public void removeSubscriber(Subscriber subscriber) {
        ValidatorUtils.requireNonNull(subscriber);
        this.subscribers.remove(subscriber);
    }

    public void notifySubscribers() {
        this.executorService.execute(
                () -> this.subscribers.forEach(Subscriber::notifySubscriber)
        );
    }

    int size() {
        return this.subscribers.size();
    }

    @Override
    public void onClose() {
        this.executorService.shutdownNow();
        this.subscribers.clear();
    }
}
