package hexlet.code.schemas;


import java.util.Objects;

public final class StringSchema extends BaseSchema<String> {

    StringSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    StringSchema minLength(int minSize) {
        addValidation("minLength", s -> s.length() >= minSize);
        return this;
    }

    StringSchema contains(String contain) {
        addValidation("contains", s -> s.contains(contain));
        return this;
    }
}
