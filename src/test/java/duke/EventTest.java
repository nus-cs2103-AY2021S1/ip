package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testSerialize() {
        assertEquals(Arrays.toString(new String[]{"0", "Event", "kappa", "2018-02-01T12:00"}),
                Arrays.toString(new Event("kappa", "2018-02-01 1200").serialize()));
        assertEquals(Arrays.toString(new String[]{"1", "Event", "kappa", "2018-02-01T15:00"}),
                Arrays.toString(new Event("kappa", true, "2018-02-01T15:00").serialize()));
    }
}
