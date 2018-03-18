package com.webapp.effectiveness.common.validators;

import java.util.Collection;
import java.util.Objects;

public class ValidatorUtils {
    public static <T> void nonNull(Collection<T> collection) {
        for (T value : collection) {
            Objects.requireNonNull(value);
        }
    }

    public static <T> void nonNull(T... collection) {
        for (T value : collection) {
            Objects.requireNonNull(value);
        }
    }

    public static <T> void nonNull(T element) {
        Objects.requireNonNull(element);
    }
}
