package com.webapp.effectiveness.functionalities.eventhandlers;

import com.webapp.effectiveness.common.validators.ValidatorUtils;
import com.webapp.effectiveness.functionalities.eventhandlers.AsynchronousJFXExecutor;
import com.webapp.effectiveness.functionalities.eventhandlers.clock.CycleSequenceExecutor;
import com.webapp.effectiveness.functionalities.routinecaretaker.RoutineScheduler;
import javafx.scene.control.Label;

public class EventHandlerFacade {
    private RoutineScheduler routineScheduler;
    private Label label;
    private AsynchronousJFXExecutor asynchronousJFXExecutor;
    private CycleSequenceExecutor cycleSequenceExecutor;

    public EventHandlerFacade(RoutineScheduler routineScheduler, Label label) {
        ValidatorUtils.requireNonNull(routineScheduler, label);

        this.routineScheduler = routineScheduler;
        this.label = label;
        this.asynchronousJFXExecutor = new AsynchronousJFXExecutor(200);
    }

    public void start() {
        if (this.asynchronousJFXExecutor.isRunning())
            return;

        CycleSequenceExecutor cycleSequenceExecutor =
                new CycleSequenceExecutor(routineScheduler, asynchronousJFXExecutor, label);
        asynchronousJFXExecutor.start(cycleSequenceExecutor);

        this.cycleSequenceExecutor = cycleSequenceExecutor;
    }

    public void stop( ) {
        this.asynchronousJFXExecutor.stop();
        this.cycleSequenceExecutor.kill();
    }
}
