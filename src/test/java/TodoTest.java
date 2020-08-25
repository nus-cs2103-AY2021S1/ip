import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][x] eat", new Todo("eat").toString());
    }

}
