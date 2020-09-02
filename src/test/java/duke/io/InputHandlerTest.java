package duke.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputHandlerTest extends InputHandler {

    private final InputStream standardIn = System.in;
    private ByteArrayInputStream inputStreamFeeder = new ByteArrayInputStream("duke \"test\" string".getBytes());

    @BeforeEach
    public void setup() {
        System.setIn(inputStreamFeeder);
    }

    @AfterEach
    public void tearDown() {
        inputStreamFeeder = new ByteArrayInputStream("duke \"test\" string".getBytes());
        System.setIn(standardIn);
    }

    @Test
    public void input_readTestInput_returnTestInputString() {
        assertEquals("duke \"test\" string", new InputHandler().input());
    }

}
