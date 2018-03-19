package com.webapp.effectiveness.functionalities.routinecaretaker;

import com.webapp.effectiveness.common.timeUtils.TimeUtils;
import com.webapp.effectiveness.common.validators.ValidatorUtils;

public class Cycle {
    private final Integer periodLengthInMinutes;
    private final CycleType cycleType;
    private final transient long cachePeriodLengthInMillis;

    public Cycle(Integer periodLengthInMinutes, CycleType cycleType) {
        ValidatorUtils.requireNonNull(periodLengthInMinutes, cycleType);

        this.periodLengthInMinutes = periodLengthInMinutes;
        this.cycleType = cycleType;
        this.cachePeriodLengthInMillis = TimeUtils.ToMillis.FROM_MINUTES.apply(periodLengthInMinutes);
    }

    public boolean isTimeExceeded(long alreadySpentMillis) {
        return alreadySpentMillis > this.cachePeriodLengthInMillis;
    }

    public long calculatePeriodDiff(long alreadySpentMillis) {
        if (alreadySpentMillis < cachePeriodLengthInMillis)
            throw new InvalidSequenceOfInvocationsException();

        return alreadySpentMillis - this.cachePeriodLengthInMillis;
    }

    public CycleType getCycleType( ) {
        return cycleType;
    }

    public Integer getPeriodLengthInMinutes( ) {
        return periodLengthInMinutes;
    }
}
