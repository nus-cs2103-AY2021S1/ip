package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testSerialize() {
        assertEquals(Arrays.toString(new String[]{"1", "Todo", "kappa"}),
                Arrays.toString(new Todo("kappa", true).serialize()));
    }
}
