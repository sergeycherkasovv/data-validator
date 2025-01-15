package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {


    MapSchema sizeof(int limitSize) {
        addValidation("sizeof", m -> m.size() == limitSize);
        return this;
    }

    //public MapSchema shape()
}
