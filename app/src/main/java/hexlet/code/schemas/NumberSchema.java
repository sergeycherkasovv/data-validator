package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema range(int begin, int end) {
        addValidation("range", i -> begin <= i && i <= end);
        return this;
    }

    public NumberSchema positive() {
        addValidation("positive", i -> i > 0);
        return this;
    }
}
