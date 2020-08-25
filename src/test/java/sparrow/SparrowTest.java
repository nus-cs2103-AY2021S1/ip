package sparrow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SparrowTest {

    @Test
    public void exceptionMessage() {
        assertEquals("ARR!\uD83C\uDFF4\u200D\u2620\uFE0F️ ", Sparrow.standardExceptionMessage());
    }

}
