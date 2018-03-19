package com.webapp.effectiveness.common.validators;

import java.util.Collection;
import java.util.Objects;

public enum  ValidatorUtils {
    ; // sign end of fields

    public static void requireNonNull(Collection<Object> collection) {
        for (Object value : collection) {
            Objects.requireNonNull(value);
        }
    }

    public static void requireNonNull(Object... args) {
        for (Object value : args) {
            Objects.requireNonNull(value);
        }
    }

    public static void requireNonNull(Object element) {
        Objects.requireNonNull(element);
    }
}
