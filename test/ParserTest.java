import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse_listCommand_1() {
        Parser parser = new Parser();

        try {
            assertEquals(1, parser.parse("list", 4));
        } catch (Exception | InvalidCommandException e) {
            fail();
        }
    }

    @Test
    void parse_listCommandWithSpace_1() {
        Parser parser = new Parser();

        try {
            assertEquals(1, parser.parse("   list    ", 4));
        } catch (Exception | InvalidCommandException e) {
            fail();
        }
    }

    @Test
    void parse_doneCommand_2() {
        Parser parser = new Parser();

        try {
            assertEquals(2, parser.parse("done 2", 4));
        } catch (Exception | InvalidCommandException e) {
            fail();
        }
    }

    @Test
    void parse_deleteCommand_3() {
        Parser parser = new Parser();

        try {
            assertEquals(3, parser.parse("delete 2", 4));
        } catch (Exception | InvalidCommandException e) {
            fail();
        }
    }

    @Test
    void parse_findCommand_4() {
        Parser parser = new Parser();

        try {
            assertEquals(4, parser.parse("find 2", 4));
        } catch (Exception | InvalidCommandException e) {
            fail();
        }
    }

    @Test
    void parse_numberOutOfBounds_throwInvalidCommandException() {
        Parser parser = new Parser();

        try {
            parser.parse("done 5", 4);
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Number is invalid!", e.toString());
        }
    }

    @Test
    void parse_stringInsteadOfNumber_throwInvalidCommandException() {
        Parser parser = new Parser();

        try {
            parser.parse("done string", 4);
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Please input number after the done command!", e.toString());
        }
    }

    @Test
    void getTask_multipleSpacing_throwInvalidInputException() {
        Parser parser = new Parser();
        try {
            parser.getTask("done   2");
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Sorry, I don't know what that means :(", e.toString());
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    void getTask_wrongDateFormat_throwInvalidCommandException() {
        Parser parser = new Parser();
        try {
            parser.getTask("event description description /at 2020-09-2 12:30");
        } catch (InvalidCommandException e) {
            assertEquals("Please give your date in yyyy-mm-dd format!", e.toString());
        } catch (InvalidInputException e) {
            fail();
        }
    }

    @Test
    void getTask_wrongTimeFormat_throwInvalidCommandException() {
        Parser parser = new Parser();

        try {
            parser.getTask("event description description /at 2020-09-21 1:30");
        } catch (InvalidCommandException e) {
            assertEquals("Please give your time in hh:mm format!", e.toString());
        } catch (InvalidInputException e) {
            fail();
        }
    }

    @Test
    void getDate_missingTime_throwInvalidCommandException() {
        Parser parser = new Parser();

        try {
            parser.getTask("event description description /at 2020-09-21");
        } catch (InvalidCommandException e) {
            assertEquals("Missing time", e.toString());
        } catch (InvalidInputException e) {
            fail();
        }
    }

    @Test
    void getTask_missingDateTime_throwInvalidCommandException() {
        Parser parser = new Parser();

        try {
            parser.getTask("event description description /at ");
        } catch (InvalidCommandException e) {
            assertEquals("Sorry, missing event date and time :(", e.toString());
        } catch (InvalidInputException e) {
            fail();
        }
    }

}