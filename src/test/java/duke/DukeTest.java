package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {

    /**
     * Tests if the parser returns the correct Command
     * @throws DukeException if fullCommand is not valid
     */
    @Test
    public void parserTestToDo() throws DukeException {
        String toDoString = new AddToDoCommand(new String[] {"todo", "eat good food"}).toString();
        assertEquals(Parser.parse("todo eat good food").toString(),
                toDoString);
    }

    /**
     * Tests if new deadline object returns appropriate string
     */
    @Test
    public void deadlineTest() {
        assertEquals(new Deadline("study ", "23 November 2019").toString(),
                "[DEADLINE] [\u2718] study (by:23 November 2019)");
    }

    /**
     * Tests if new event object returns appropriate string
     */
    @Test
    public void eventTest() {
        assertEquals(new Event("Hackathon ", "NUS U town").toString(),
                "[EVENT] [\u2718] Hackathon (at:NUS U town)");
    }
}
