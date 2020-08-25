package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parserTestForBoolean() throws IOException {
        Parser p = new Parser("bye", new Ui(), new Storage("./task_list.txt"), new TaskList());
        p.parseCommand();
        assertEquals(false, Duke.running);
    }

    @Test
    public void parserTestForPrintList() throws IOException {
        Parser p = new Parser("list", new Ui(), new Storage("./task_list.txt"), new TaskList());
        p.parseCommand();
        assertEquals(false, Duke.running);
    }
}
