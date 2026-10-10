package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import model.annotation.Positive;
import model.annotation.MaxLength;

public class AnnotationValidator {

    public static String[] validate(Object obj) {

        List<String> errors = new ArrayList<>();

        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getSuperclass().getDeclaredFields()) {

            try {

                field.setAccessible(true);

                Object value = field.get(obj);

                if (field.isAnnotationPresent(Positive.class)) {

                    Positive annotation =
                            field.getAnnotation(Positive.class);

                    if (value instanceof Number &&
                            ((Number) value).longValue() <= 0) {

                        errors.add(
                                field.getName() + " " +
                                annotation.message()
                        );
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {

                    MaxLength annotation =
                            field.getAnnotation(MaxLength.class);

                    if (value instanceof String &&
                            ((String) value).length() > annotation.value()) {

                        errors.add(
                                field.getName() +
                                " length must be <= " +
                                annotation.value()
                        );
                    }
                }

            } catch (IllegalAccessException e) {
                errors.add("Unable to access field: " + field.getName());
            }
        }

        return errors.toArray(new String[0]);
    }
}