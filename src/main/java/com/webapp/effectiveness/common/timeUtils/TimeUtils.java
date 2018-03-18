package com.webapp.effectiveness.common.timeUtils;

public enum  TimeUtils {
    ; // sign end of fields

    public interface CalculateTimeTransition {
        long apply(long quantity);
    }

    public enum ToMillis implements CalculateTimeTransition {
        FROM_MINUTES {
            @Override
            public long apply(long quantity) {
                return (60 * 1000) * quantity;
            }
        }
    }
}
