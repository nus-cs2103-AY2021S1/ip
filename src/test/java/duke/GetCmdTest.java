package duke;

import duke.exceptions.NoSuchOrderException;
import duke.tool.ValidInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class GetCmdTest {
    @Test
    public void GestCmdTest1() throws NoSuchOrderException {
        assertEquals(ValidInput.getCmdType("todo i".split(" ", 2)[0]).name(), "TODO");
    }
}
