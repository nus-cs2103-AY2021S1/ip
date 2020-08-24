import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void todoParseTest(){
        String testInput = "todo read book";
        Parser testParser = new Parser(testInput);
        assertEquals("todo", testParser.getTaskCategory());
    }

    @Test
    public void eventTimeParseTest() throws DukeException {
        String testInput = "event read book /by 2020-14-05";
        Parser testParser = new Parser(testInput);
        assertEquals("2020-14-05", testParser.getTaskTime());
    }

    @Test
    public void eventAddTest() throws DukeException {
        String testInput = "event finish level-7 /by 2020-08-25";
        Parser testParser = new Parser(testInput);
        TaskList tasks = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        storage.load();
        try {
            testParser.parse().execute(tasks, new Ui(), storage);
            assertEquals(1, tasks.getSize());
        } catch (DukeException e) {
            System.out.print(e.getMessage());
        }
    }
}