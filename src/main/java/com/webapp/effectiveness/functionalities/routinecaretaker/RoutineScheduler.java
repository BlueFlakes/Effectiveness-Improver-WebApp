package com.webapp.effectiveness.functionalities.routinecaretaker;

import com.webapp.effectiveness.common.executorWrappers.ExecutorCachedThreadPoolWrapper;
import com.webapp.effectiveness.common.validators.ValidatorUtils;
import com.webapp.effectiveness.functionalities.statistics.LifeSpan;
import com.webapp.effectiveness.functionalities.task.Task;
import com.webapp.effectiveness.functionalities.timebeater.ElapsedTimeBeater;

public class RoutineScheduler implements Runnable {
    private LifeSpan lifeSpan = new LifeSpan();
    private ExecutorCachedThreadPoolWrapper threadPoolWrapper = ExecutorCachedThreadPoolWrapper.getInstance();

    private Routine routine;
    private ElapsedTimeBeater elapsedTimeBeater;
    private Task task;

    public RoutineScheduler(Routine routine, ElapsedTimeBeater elapsedTimeBeater, Task task) {
        ValidatorUtils.requireNonNull(routine, elapsedTimeBeater, task);

        this.routine = routine;
        this.elapsedTimeBeater = elapsedTimeBeater;
        this.task = task;

        begin();
    }

    private void begin() {
        this.threadPoolWrapper.execute(elapsedTimeBeater);
    }

    private void turnOnTaskExecution() {
        this.elapsedTimeBeater.addSubscriber(this.lifeSpan);
    }

    private void turnOffTaskExecution() {
        this.elapsedTimeBeater.removeSubscriber(this.lifeSpan);
    }

    @Override
    public void run( ) {
        while (true) {
            Cycle currentCycle = this.routine.nextCycle();
            CycleType cycleType = currentCycle.getCycleType();

            if (currentCycle.isTimeExceeded(this.lifeSpan.getLength())
                    && cycleType == CycleType.WORK_TIME) {


            }
        }
    }
}
