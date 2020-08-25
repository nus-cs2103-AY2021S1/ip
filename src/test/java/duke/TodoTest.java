package duke;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testSerialize() {
        assertEquals(Arrays.toString(new String[]{"1", "Todo", "kappa"}), 
                Arrays.toString(new Todo("kappa", true).serialize()));
    }
}
