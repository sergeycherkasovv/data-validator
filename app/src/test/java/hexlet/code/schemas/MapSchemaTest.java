package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {
    private MapSchema schema;

    @BeforeEach
    void beforeEach() {
        schema = new Validator().map();
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
    void combinationOfConstraintsTest() {
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

    @Test
    void shapeTest() {
        var schemaString = new Validator().string();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", schemaString.required());
        schemas.put("lastName", schemaString.required().minLength(2));

        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human1.put("firstName", "Jane");
        human1.put("lastName", "S");
        assertFalse(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human1.put("firstName", null);
        human1.put("lastName", "Smith");
        assertFalse(schema.isValid(human3));
    }
}
