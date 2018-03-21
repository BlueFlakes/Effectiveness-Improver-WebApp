package com.webapp.effectiveness.functionalities.eventhandlers.clock;

import com.webapp.effectiveness.common.myfunctionalinterfaces.Procedure;
import com.webapp.effectiveness.functionalities.eventhandlers.AsynchronousJFXExecutor;
import com.webapp.effectiveness.functionalities.routinecaretaker.CycleType;
import com.webapp.effectiveness.functionalities.routinecaretaker.RoutineScheduler;
import javafx.scene.control.Label;

public class CycleSequenceExecutor implements Procedure {
    private RoutineScheduler.CycleRunner cycleRunner;
    private AsynchronousJFXExecutor asynchronousJFXExecutor;
    private RoutineScheduler routineScheduler;
    private ClockAnimation clockAnimation;

    public CycleSequenceExecutor(RoutineScheduler routineScheduler,
                                 AsynchronousJFXExecutor asynchronousJFXExecutor,
                                 Label spaceForAnimation) {

        this.routineScheduler = routineScheduler;
        this.asynchronousJFXExecutor = asynchronousJFXExecutor;

        this.cycleRunner = routineScheduler.runNextCycle();
        this.clockAnimation = new ClockAnimation(spaceForAnimation);
    }


    @Override
    public void invoke( ) {
        RoutineScheduler.CycleRunner currentRunner = manageExecutionProcess();
        this.clockAnimation.showCurrentTime(currentRunner);
    }

    private RoutineScheduler.CycleRunner manageExecutionProcess() {
        if (cycleRunner.isCycleFinished()) {
            if (cycleRunner.getCycleType() == CycleType.FREE_TIME) {
                asynchronousJFXExecutor.stop();
            } else {
                this.cycleRunner = routineScheduler.runNextCycle();
            }
        }

        return this.cycleRunner;
    }

    public void kill() {
        cycleRunner.kill();
    }
}