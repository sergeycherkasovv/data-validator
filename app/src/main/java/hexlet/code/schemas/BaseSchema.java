package hexlet.code.schemas;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private Map<String, Predicate<T>> validations = new HashMap<>();

    protected final void addValidation(String key, Predicate<T> validation) {
        this.validations.put(key, validation);
    }

    public final boolean isValid(T value) {
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

    /**
     * Marks the schema as required, ensuring that the value being validated is not null.
     * <p>
     * This method adds a "required" validation to the schema, which checks that the provided value
     * is not {@code null}. If the validation fails, the schema will indicate an error.
     * </p>
     *
     * @return the current {@code BaseSchema} instance for method chaining.
     */
    public BaseSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }
}
