package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {
    private final Validator valid = new Validator();
    private StringSchema schema;

    @BeforeEach
    void beforeEach() {
        schema = valid.string();
    }

    @Test
    void requiredTest() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));

        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));

        assertTrue(schema.isValid("notNull"));
    }

    @Test
    void minLengthTest() {
        schema.minLength(6);

        assertFalse(schema.isValid("fail"));

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("Google"));
        assertTrue(schema.isValid("I like tests"));
    }

    @Test
    void containsTest() {
        schema.contains("li");

        assertFalse(schema.isValid("bad"));

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("I like tests"));
    }

    @Test
    void combinationOfConstraintsTest() {
        schema.minLength(7).contains("go");

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid("google"));

        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("no argument from 'contains'"));
        assertTrue(schema.isValid("there is an argument 'go' from 'contains'"));

        schema.minLength(4);
        assertTrue(schema.isValid("google"));
    }
}
