import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextAndTaskConverterTest {

    @Test
    public void testTextConverter() {
        assertEquals("[T]✘homework", TextAndTaskConverter.textConverter("T ## 0 ## homework").toString());
    }
}