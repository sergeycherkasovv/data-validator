import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Main;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void name() {
        assertEquals("Hello world!", Main.main());
    }
}
