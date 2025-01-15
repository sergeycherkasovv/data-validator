package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private Map<String, Predicate<T>> validations = new HashMap<>();

    protected void addValidation(String key, Predicate<T> validation) {
        this.validations.put(key, validation);
    }

    boolean isValid(T value) {
        if (validations.containsKey("required") && value == null) {
            return false;
        }
        if (value == null) {
            return true;
        }

        return validations.values()
                .stream()
                .allMatch(t -> t.test(value));
    }
}
