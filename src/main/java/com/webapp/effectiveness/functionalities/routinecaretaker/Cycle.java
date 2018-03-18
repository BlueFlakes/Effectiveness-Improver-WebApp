package com.webapp.effectiveness.functionalities.routinecaretaker;

import com.webapp.effectiveness.common.timeUtils.TimeUtils;

public class Cycle {
    private Integer periodLengthInMinutes;
    private CycleType cycleType;

    enum CycleType {
        WORK_TIME,
        FREE_TIME;
    }

    public Cycle(Integer periodLengthInMinutes, CycleType cycleType) {
        this.periodLengthInMinutes = periodLengthInMinutes;
        this.cycleType = cycleType;
    }

    public boolean isTimeExceeded(long alreadySpentMillis) {
        final long cyclePeriodInMillis =
                TimeUtils.ToMillis.FROM_MINUTES.apply(this.periodLengthInMinutes);

        return cyclePeriodInMillis < alreadySpentMillis;
    }

    public Integer getPeriodLengthInMinutes( ) {
        return periodLengthInMinutes;
    }
}
