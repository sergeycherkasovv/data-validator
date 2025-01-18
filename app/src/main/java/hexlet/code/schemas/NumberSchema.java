package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    public NumberSchema range(int begin, int end) {
        addValidation("range", i -> begin <= i && i <= end);
        return this;
    }

    public NumberSchema positive() {
        addValidation("positive", i -> i > 0);
        return this;
    }
}

