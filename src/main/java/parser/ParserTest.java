package parser;

import java.util.ArrayList;
import data.TaskList;
import storage.Storage;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test the Parser.
 */
public class ParserTest {
    TaskList tasks = new TaskList(new ArrayList<>());
    Storage storage = new Storage(".\\data_test.txt");
    Parser parser = new Parser(tasks, storage);

    @Test
    public void parse_addTask_tasksAdded() {
        parser.parse("todo Sleep");
        parser.parse("event Drink water /at 1998-08-02");
        parser.parse("deadline Eat /by 1998-02-08");
        assertEquals(tasks.get(0).toString().contains("[T][✘] Sleep"), true );
        assertEquals(tasks.get(1).toString().contains("[E][✘] Drink water (at: 1998-08-02)"), true );
        assertEquals(tasks.get(2).toString().contains("[D][✘] Eat (by: 1998-02-08)"), true );
    }

    @Test
    public void parse_doTask_tasksDone() {
        parser.parse("todo Sleep");
        parser.parse("event Drink water /at 1998-08-02");
        parser.parse("deadline Eat /by 1998-02-08");
        parser.parse("done 1");
        parser.parse("done 2");
        parser.parse("done 3");
        assertEquals(tasks.get(0).toString().contains("[T][✓] Sleep"), true );
        assertEquals(tasks.get(1).toString().contains("[E][✓] Drink water (at: 1998-08-02)"), true );
        assertEquals(tasks.get(2).toString().contains("[D][✓] Eat (by: 1998-02-08)"), true );
    }

    @Test
    public void parse_invalidInput_noTasksAdded() {
        parser.parse("asd");
        assertEquals(tasks.size(), 0);
    }



}
