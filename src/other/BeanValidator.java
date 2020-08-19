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
    
    /**
     * 对象类型的验证
     * @param first
     * @param objects
     * @return
     */
    public static Map<String, String> validateObject(Object first, Object... objects) {
        if (objects == null && objects.length > 1) {
            return validateList(Lists.asList(first, objects));
        } else {
            return validate(first, new Class[0]);
        }
    }

    /**
     * 检查参数是否符合类型
     * @param param
     * @throws RuntimeException
     */
    public static void check(Object param) throws RuntimeException {
        Map<String, String> map = BeanValidator.validateObject(param);
        if (MapUtils.isNotEmpty(map)) {
            throw new RuntimeException(map.toString());
        }
    }
    
}
