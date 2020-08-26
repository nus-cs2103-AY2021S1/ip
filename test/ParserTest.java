import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse() {
        try {
            Parser.parse("done 1");
        } catch (InvalidInputException e) {
            assertEquals("Sorry, I don't know what that means :(", e.getMessage());
        } catch (InvalidCommandException e) {
            assertEquals("Number is invalid!", e.getMessage());
        }
    }

    @Test
    void getTask() {
        try {
            Parser.getTask("random");
            fail();
        } catch (InvalidCommandException e) {
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Sorry, I don't know what that means :(", e.getMessage());
        }
    }

    @Test
    void getTaskInvalidInput() {
        try {
            Parser.getTask("todo ");
        } catch (InvalidCommandException e) {
            assertEquals("Sorry, the description of a todo cannot be empty :(", e.getMessage());
        } catch (InvalidInputException e) {
            fail();
        }
    }
}