package com.webapp.effectiveness.common.timeUtils;

public class TimeUtils {

    public interface CalculatePattern {
        long apply(long quantity);
    }

    public enum ToMillis implements CalculatePattern {
        FROM_MINUTES {
            @Override
            public long apply(long quantity) {
                return (60 * 1000) * quantity;
            }
        };
    }
}
