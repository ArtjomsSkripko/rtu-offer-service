package offer.validation;

import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEnumValidator implements ConstraintValidator<ValidEnum, String> {

    private Class<? extends Enum<?>> enumeration;
    private Boolean required;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        enumeration = constraintAnnotation.value();
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return !required;
        }
        List<? extends Enum<?>> enumList = Arrays.asList(enumeration.getEnumConstants());
        if (!enumList.isEmpty()) {
            for (Enum<?> currentEnum : enumList) {
                if (value.equalsIgnoreCase(currentEnum.name())) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }
}


