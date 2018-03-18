package com.webapp.effectiveness.functionalities.routinecaretaker;

import com.webapp.effectiveness.common.datastructures.lists.CircularList;
import java.util.Objects;


public class Routine {
    private final CircularList<Cycle> cycles;

    {
        this.cycles = CircularList.createCircularArrayList();
    }

    public void addCycle(Cycle cycle) {
        Objects.requireNonNull(cycle);
        this.cycles.add(cycle);
    }

    public void removeCycle(Cycle cycle) {
        Objects.requireNonNull(cycle);
        this.cycles.remove(cycle);
    }


    public static void main(String[] args) {
        Routine routine = new Routine();
    }
}
