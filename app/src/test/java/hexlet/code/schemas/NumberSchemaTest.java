package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {
    Validator valid = new Validator();
    NumberSchema schema;

    @BeforeEach
    void beforeEach() {
        schema = valid.number();
    }

    @Test
    void requiredTest() {
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(1));
    }

    @Test
    void positiveTest() {
        schema.positive();

        assertFalse(schema.isValid(-1));
        assertFalse(schema.isValid(0));

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(1));
    }

    @Test
    void rangeTest() {
        schema.range(-5, 5);

        assertFalse(schema.isValid(-6));
        assertFalse(schema.isValid(25));

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(-1));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(5));
        assertTrue(schema.range(5, 5).isValid(5));
    }

    @Test
    void combinationOfConstraintsTest() {
        schema.positive().range(-1, 1);

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(-1));
        assertTrue(schema.isValid(1));

        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(2));
    }
}
