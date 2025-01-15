package hexlet.code.schemas;


import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    NumberSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    NumberSchema range(int begin, int end) {
        addValidation("range", i -> begin <= i && i <= end);
        return this;
    }

    NumberSchema positive() {
        addValidation("positive", i -> i > 0);
        return this;
    }
}
