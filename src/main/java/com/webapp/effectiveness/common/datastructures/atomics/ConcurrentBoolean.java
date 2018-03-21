package com.webapp.effectiveness.common.datastructures.atomics;

import java.util.concurrent.atomic.AtomicBoolean;

public class ConcurrentBoolean {
    private AtomicBoolean atomicBoolean;

    public ConcurrentBoolean(boolean value) {
        this.atomicBoolean = new AtomicBoolean(value);
    }

    public void setTrue() {
        safeSetNewValue(true);
    }

    public void setFalse() {
        safeSetNewValue(false);
    }

    private void safeSetNewValue(boolean newValue) {
        while(true) {
            boolean existingValue = lookUpCurrentState();
            if(this.atomicBoolean.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    public boolean lookUpCurrentState() {
        return this.atomicBoolean.get();
    }
}
