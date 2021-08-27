package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parsingEvents() {
        Parser parser = new Parser();
        parser.parsing("todo homework");
        assertEquals(parser.getCommand(), "todo");
        assertEquals(parser.getTaskName(), "homework");

        parser.parsing("deadline homework /by 2021-08-26");
        assertEquals(parser.getCommand(), "deadline");
        assertEquals(parser.getTaskName(), "homework");
        assertEquals(parser.getTimeline(), "2021-08-26");

        parser.parsing("event homework /at monday");
        assertEquals(parser.getCommand(), "event");
        assertEquals(parser.getTaskName(), "homework");
        assertEquals(parser.getTimeline(), "monday");
    }

    @Test
    void parsingCommands() {
        Parser parser = new Parser();
        parser.parsing("done 2");
        assertEquals(parser.getCommand(), "done");
        assertEquals(parser.getTaskName(), "2");

        parser.parsing("remove 3");
        assertEquals(parser.getCommand(), "remove");
        assertEquals(parser.getTaskName(), "3");

        Parser parser2 = new Parser();
        parser2.parsing("bye");
        assertEquals(parser2.getCommand(), "bye");
    }
}