package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DukeTest {

    @Test
    public void getResponse_validInput_success() {
        Duke duke = new Duke();
        String response = duke.getResponse("bye");
        assertEquals("Bye! Hope to see you again soon!\n", response);
    }

    @Test
    public void getResponse_invalidInput_success() {
        Duke duke = new Duke();
        String response = duke.getResponse("bla");
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means.\n"
                + "Type 'help' to display the list of commands available.\n", response);
    }

}
