package com.webapp.effectiveness.common.datastructures;

import java.util.Objects;

public final class Tuple {

    // Tuple should be never instantiable
    private Tuple() {
        throw new AssertionError();
    }

    public static <T> Unit<T> of(T v1) {
        return new Unit<>(v1);
    }

    public static <T, U> Pair<T, U> of(T v1, U v2) {
        return new Pair<>(v1, v2);
    }

    public static <T, U, P> Triplet<T, U, P> of(T v1, U v2, P v3) {
        return new Triplet<>(v1, v2, v3);
    }

    private static void raiseNullPointerIfAskedFieldIsNull(Object fieldValue) {
        if (Objects.isNull(fieldValue))
            throw new NullPointerException("Asked field is null.");
    }

    public static class Unit<T> {
        private final T value1;

        private Unit(T value1) {
            raiseNullPointerIfAskedFieldIsNull(value1);
            this.value1 = value1;
        }

        public T getFirst( ) {
            return value1;
        }
    }

    public static class Pair<T, U> extends Unit<T> {
        private final U value2;

        private Pair(T item1, U value2) {
            super(item1);

            raiseNullPointerIfAskedFieldIsNull(value2);
            this.value2 = value2;
        }

        public U getSecond( ) {
            return value2;
        }
    }

    public static class Triplet<T, U, P> extends Pair<T, U> {
        private final P value3;

        private Triplet(T item1, U item2, P value3) {
            super(item1, item2);

            raiseNullPointerIfAskedFieldIsNull(value3);
            this.value3 = value3;
        }

        public P getThird( ) {
            return value3;
        }
    }
}
