package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        addValidation("required", s -> s != null && !s.equals(""));
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

