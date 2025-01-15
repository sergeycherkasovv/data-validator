package hexlet.code.schemas;


public class StringSchema extends BaseSchema<String> {


    StringSchema minLength(int minSize) {
        addValidation("minLength", s -> s.length() >= minSize);
        return this;
    }

    StringSchema contains(String contain) {
        addValidation("contains", s -> s.contains(contain));
        return this;
    }
}
