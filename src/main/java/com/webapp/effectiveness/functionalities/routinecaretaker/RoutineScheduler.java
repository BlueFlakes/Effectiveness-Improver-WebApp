package com.webapp.effectiveness.functionalities.routinecaretaker;

import com.webapp.effectiveness.common.ApplicationCloseable;
import com.webapp.effectiveness.common.validators.ValidatorUtils;
import com.webapp.effectiveness.functionalities.statistics.LifeSpan;
import com.webapp.effectiveness.functionalities.task.Task;
import com.webapp.effectiveness.functionalities.timebeater.ElapsedTimeBeater;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoutineScheduler implements ApplicationCloseable {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private Routine routine;
    private ElapsedTimeBeater elapsedTimeBeater;
    private Task task;

    public RoutineScheduler(Routine routine, ElapsedTimeBeater elapsedTimeBeater, Task task) {
        ValidatorUtils.requireNonNull(routine, elapsedTimeBeater, task);

        this.routine = routine;
        this.elapsedTimeBeater = elapsedTimeBeater;
        this.task = task;
    }

    @Override
    public void onClose( ) {
        this.executorService.shutdownNow();
    }

    public CycleRunner runNextCycle() {
        Cycle currentCycle = this.routine.nextCycle();

        CycleRunner cycleRunner = new CycleRunner(currentCycle);
        this.executorService.execute(cycleRunner);

        return cycleRunner;
    }

    public class CycleRunner implements Runnable {
        private LifeSpan lifeSpan = new LifeSpan();
        private Cycle cycle;

        public CycleRunner(Cycle cycle) {
            this.cycle = cycle;
        }

        private long convertCurrentLifeSpanToMillis() {
            return this.lifeSpan.getLength() * elapsedTimeBeater.getSleepingPeriodInMillis();
        }

        public long calculateHowManyMillisLeftToFinishCycle() {
            long currentLifeSpanMillis = convertCurrentLifeSpanToMillis();
            return this.cycle.calculatePeriodDiff(currentLifeSpanMillis);
        }

        public boolean isCycleFinished() {
            long currentLifeSpanMillis = convertCurrentLifeSpanToMillis();
            return this.cycle.isTimeExceeded(currentLifeSpanMillis);
        }

        @Override
        public void run( ) {
            try {
                elapsedTimeBeater.addSubscriber(this.lifeSpan);
                handleCycleExecution();

            } catch (InterruptedException e) {
                // not need any care
            } finally {
                elapsedTimeBeater.removeSubscriber(this.lifeSpan);
            }
        }

        private void handleCycleExecution() throws InterruptedException {
            boolean isTimeExceeded = false;
            while (!isTimeExceeded) {
                Thread.sleep(100);
                isTimeExceeded = isCycleFinished();
            }
        }
    }
}
