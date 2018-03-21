package com.webapp.effectiveness.functionalities.eventhandlers;

import com.webapp.effectiveness.common.datastructures.atomics.ConcurrentBoolean;
import com.webapp.effectiveness.common.myfunctionalinterfaces.Procedure;
import com.webapp.effectiveness.common.validators.ValidatorUtils;
import com.webapp.effectiveness.functionalities.routinecaretaker.InvalidSequenceOfInvocationsException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class AsynchronousJFXExecutor {
    private ConcurrentBoolean isTurnedOn;
    private Procedure procedure;
    private Timeline timeline;

    public AsynchronousJFXExecutor(long sleepingDuration) {
        onStartCreateTimeline(sleepingDuration);
        this.isTurnedOn = new ConcurrentBoolean(false);
    }

    private void onStartCreateTimeline(long sleepingDuration) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(sleepingDuration),
                        event -> this.procedure.invoke()
                ));

        timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline = timeline;
    }

    public void start(Procedure procedure) {
        if (isRunning())
            throw new InvalidSequenceOfInvocationsException();
        ValidatorUtils.requireNonNull(procedure);

        this.procedure = procedure;
        this.isTurnedOn.setTrue();
        this.timeline.play();
    }

    public void stop() {
        this.isTurnedOn.setFalse();
        this.timeline.stop();
    }

    public boolean isRunning() {
        return this.isTurnedOn.lookUpCurrentState();
    }
}
