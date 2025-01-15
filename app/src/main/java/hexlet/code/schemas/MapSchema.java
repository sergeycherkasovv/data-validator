package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    MapSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    MapSchema sizeof(int limitSize) {
        addValidation("sizeof", m -> m.size() == limitSize);
        return this;
    }

    //public MapSchema shape()
}
