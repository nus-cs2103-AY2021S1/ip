package sparrow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import sparrow.data.exceptions.SparrowException;


public class SparrowTest {

    @Test
    public void exceptionMessage() {
        assertEquals("ARR!\uD83C\uDFF4\u200D\u2620\uFE0F️ ", SparrowException.STANDARD_EXCEPTION_MESSAGE);
    }

}
