package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.response.Response;

public class ExitCommandTest {
    @Test
    public void execute() {
        Response response = ExitCommand.execute();
        String expected = "Bye. Hope to see you again soon!";
        assertEquals(expected, response.getMessage());
    }
}
