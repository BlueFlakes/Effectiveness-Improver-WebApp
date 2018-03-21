package com.webapp.effectiveness.functionalities.eventhandlers.clock;

import com.webapp.effectiveness.functionalities.routinecaretaker.RoutineScheduler;
import javafx.scene.control.Label;

class ClockAnimation {
    private Label label;

    public ClockAnimation(Label label) {
        this.label = label;
    }

    public void showCurrentTime(RoutineScheduler.CycleRunner cycleRunner) {
        this.label.setText(getFormattedTime(cycleRunner));
    }

    private String getFormattedTime(RoutineScheduler.CycleRunner cycleRunner) {
        long lifetimeInSeconds = cycleRunner.calculateHowManyMillisLeftToFinishCycle() / 1000;
        String minutes = String.valueOf(lifetimeInSeconds / 60);
        String seconds = String.valueOf(lifetimeInSeconds % 60);

        return fillFirstPos(minutes) + ":" + fillFirstPos(seconds);
    }

    private String fillFirstPos(String timeFormat) {
        if (timeFormat.length() == 1) {
            return "0" + timeFormat;
        }

        return timeFormat;
    }
}
