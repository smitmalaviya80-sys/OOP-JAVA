import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {
    @NotBlank
    @MaxLength(20)
    String username;

    @NotBlank
    @MaxLength(30)
    String email;

    @MaxLength(10)
    String city;

    SignupForm(String username, String email, String city) {
        this.username = username;
        this.email = email;
        this.city = city;
    }
}

public class FormValidator {

    public static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                String value = (String) field.get(obj);

                if (field.isAnnotationPresent(NotBlank.class)) {
                    if (value == null || value.trim().isEmpty()) {
                        errors.add(field.getName() + " must not be blank");
                    }
                }

                MaxLength maxLength = field.getAnnotation(MaxLength.class);

                if (maxLength != null && value != null) {
                    if (value.length() > maxLength.value()) {
                        errors.add(field.getName() + " exceeds maximum length of "
                                + maxLength.value());
                    }
                }

            } catch (IllegalAccessException e) {
                errors.add("Cannot access " + field.getName());
            }
        }

        return errors;
    }

    public static void main(String[] args) {

        SignupForm form = new SignupForm(
                "",
                "verylongemailaddress@example.com",
                "AnandCity"
        );

        List<String> errors = validate(form);

        if (errors.isEmpty()) {
            System.out.println("Form is valid");
        } else {
            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}