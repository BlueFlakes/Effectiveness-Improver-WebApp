package com.webapp.effectiveness.functionalities.routinecaretaker;

import com.webapp.effectiveness.common.datastructures.lists.CircularList;

public class Routine {
    private final CircularList<Cycle> cycles;

    {
        this.cycles = CircularList.createCircularArrayList();
    }

    public void addCycle(Cycle cycle) {
        this.cycles.add(cycle);
    }

    public void removeCycle(Cycle cycle) {
        this.cycles.remove(cycle);
    }

    public Cycle nextCycle() {
        return this.cycles.next();
    }
}
