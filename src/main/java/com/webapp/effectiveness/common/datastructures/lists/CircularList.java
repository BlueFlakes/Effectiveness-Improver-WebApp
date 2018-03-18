package com.webapp.effectiveness.common.datastructures.lists;

import java.util.*;
import java.util.function.Supplier;

public class CircularList<T> {
    private List<T> container;
    private int indicator = 0;

    private CircularList() {}

    public static <T> CircularList<T> createCircularArrayList() {
        CircularList<T> circularList = new CircularList<>();
        circularList.setContainer(ArrayList::new);

        return circularList;
    }

    public static <T> CircularList<T> createCircularLinkedList() {
        CircularList<T> circularList = new CircularList<>();
        circularList.setContainer(LinkedList::new);

        return circularList;
    }

    private void setContainer(Supplier<List<T>> listSupplier) {
        if (this.container != null)
            throw new IllegalStateException("Shouldn't be invoked after creation.");

        this.container = listSupplier.get();
    }

    public void add(T o) {
        Objects.requireNonNull(o);
        this.container.add(o);
    }

    public void remove(T o) {
        Objects.requireNonNull(o);
        this.container.remove(o);
    }

    public T next() {
        if (this.container.isEmpty())
            throw new EmptyContainerException();

        int nextElementIndex = indicator++ % this.container.size();
        return container.get(nextElementIndex);
    }
}
