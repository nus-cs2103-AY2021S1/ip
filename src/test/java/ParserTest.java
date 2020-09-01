import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import data.TaskList;
import parser.Parser;
import storage.Storage;


/**
 * Test the Parser.
 */
public class ParserTest {
    private TaskList tasks = new TaskList(new ArrayList<>());
    private Storage storage = new Storage(".\\data_test.txt");
    private Parser parser = new Parser(tasks, storage);

    @Test
    public void parse_addTask_tasksAdded() {
        parser.parse("todo Sleep");
        parser.parse("event Drink water /at 1998-08-02");
        parser.parse("deadline Eat /by 1998-02-08");
        assertEquals(tasks.get(0).toString().contains("[T][\u2718] Sleep"), true);
        assertEquals(tasks.get(1).toString().contains("[E][\u2718] Drink water (at: 1998-08-02)"), true);
        assertEquals(tasks.get(2).toString().contains("[D][\u2718] Eat (by: 1998-02-08)"), true);
    }

    @Test
    public void parse_doTask_tasksDone() {
        parser.parse("todo Sleep");
        parser.parse("event Drink water /at 1998-08-02");
        parser.parse("deadline Eat /by 1998-02-08");
        parser.parse("done 1");
        parser.parse("done 2");
        parser.parse("done 3");
        assertEquals(tasks.get(0).toString().contains("[T][\u2713] Sleep"), true);
        assertEquals(tasks.get(1).toString().contains("[E][\u2713] Drink water (at: 1998-08-02)"), true);
        assertEquals(tasks.get(2).toString().contains("[D][\u2713] Eat (by: 1998-02-08)"), true);
    }

    @Test
    public void parse_invalidInput_noTasksAdded() {
        parser.parse("asd");
        assertEquals(tasks.size(), 0);
    }



}
