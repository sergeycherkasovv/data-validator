package hexlet.code.schemas;

import java.util.Objects;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    public StringSchema minLength(int minSize) {
        addValidation("minLength", s -> s.length() >= minSize);
        return this;
    }

    public StringSchema contains(String contain) {
        addValidation("contains", s -> s.contains(contain));
        return this;
    }
}

