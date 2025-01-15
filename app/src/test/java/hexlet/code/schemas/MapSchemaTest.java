package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapSchemaTest {
    MapSchema schema;


    @BeforeEach
    void beforeEach() {
        Validator valid = new Validator();
        schema = valid.map();
    }

    @Test
    void requiredTest() {
        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void sizeof() {
        var data = new HashMap<String, String>();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(data));

        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        schema.sizeof(2);
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));

        data.put("key3", "value3");
        assertFalse(schema.isValid(data));

        schema.sizeof(1);
        assertFalse(schema.isValid(data));
    }

    @Test
    void CombinationOfVerificationTest() {
        var data = new HashMap<String, String>();
        schema.sizeof(1);
        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(data));

        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        data.put("key2", "value3");
        assertFalse(schema.isValid(data));
    }

//    @Test
//    void shapeTest() {
//        Map<String, BaseSchema<?>> schemas = new HashMap<>();
//        schemas.put("firstName", valid.string().minLength(5).required());
//        schemas.put("firstName", valid.string().required().minLength(5));
//        schemas.put("age", valid.number().positive().range(5, 25).required());
//        schemas.put("families", valid.map().sizeof(3));
//
//
//    }
}
