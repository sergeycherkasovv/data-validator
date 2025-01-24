package hexlet.code.schemas;


import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

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
