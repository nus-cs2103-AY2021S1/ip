import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void getBorder() {
        assertEquals("____________________________________________________________\n",
                new Ui().getBorder());
    }
}
