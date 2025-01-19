package hexlet.code.schemas;


import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int limitSize) {
        addValidation("sizeof", m -> m.size() == limitSize);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        addValidation(
                "shape",
                    map -> schemas.entrySet()
                                .stream()
                                .allMatch(e ->
                                        e.getValue().isValid(((String) map.get(e.getKey())))
                                )
        );
        return this;
    }
}
