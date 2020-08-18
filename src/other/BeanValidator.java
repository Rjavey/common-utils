package other;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

public class BeanValidator {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T> Map<String,String> validate(T t, Class...groups){
        Validator validator = validatorFactory.getValidator();
        Set validateResult = validator.validate(t,groups);
        if (validateResult.isEmpty()){
            return Collections.emptyMap();
        } else {;
            LinkedHashMap errors = new LinkedHashMap();
            Iterator iterable = validateResult.iterator();
            while (iterable.hasNext()){
                ConstraintViolation violation = (ConstraintViolation) iterable.next();
                errors.put(violation.getPropertyPath().toString(),violation.getMessage().toString());
            }
            return errors;
        }
    }

    public static Map<String,String> validateList(Collection<?> collection){
        Iterator iterator = collection.iterator();
        Map errors;
        do {
            if (!iterator.hasNext()) {
                return Collections.emptyMap();
            }
            errors = validate(iterator.next(),new Class[0]);
        } while (errors.isEmpty());
        return errors;
    }
}
