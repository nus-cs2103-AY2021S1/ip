import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    Parser parser = new Parser();

    @Test
    public void invalidUserInputTest() throws IOException, DukeException {
        try {
            parser.processOtherActionInput("anyhow", new TaskList(), new Ui());
        } catch (DukeException e) {
            assertEquals(e.toString(), "Duke is too dumb, Duke dunno what you mean");
        }
    }

    @Test
    public void deadlineWithoutDescriptionTest() throws ToDoException, eventException {
        try {
            parser.processAddTaskInput("deadline", new TaskList(), new Ui());
        } catch (deadlineException e) {
            assertEquals(e.toString(), "what deadline gimme smth to write pls");
        }
    }
}
