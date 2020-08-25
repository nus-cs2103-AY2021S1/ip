import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTest {
    @Test
    public void parserTest(){
        assertEquals(Parser.parse("list", 0), Parser.Command.LIST);
        assertEquals(Parser.parse("bye", 0), Parser.Command.BYE);
        assertEquals(Parser.parse("delete 2", 0), Parser.Command.OTHERS);
        assertEquals(Parser.parse("done 1", 0), Parser.Command.OTHERS);
        assertEquals(Parser.parse("delete 2", 5), Parser.Command.DELETE);
        assertEquals(Parser.whichTask, 1);
        assertEquals(Parser.parse("done 5", 5), Parser.Command.DONE);
        assertEquals(Parser.whichTask, 4);
    }

    @Test
    public void parser_createTaskTest() throws DukeException {
        String task1 = "todo 1";
        String task2 = "deadline 2 /by 2020-12-12";
        String task3 = "event 3 /at 2020-08-30";
        String taskError1 = "todo";
        String taskError2 = "event 3 /by 2020-08-30";
        String taskError3 = "deadline 2 /by 2020";
        assertEquals(Parser.createTask(task1).getCompleted(), false);
        assertEquals(Parser.createTask(task2).getType(), Task.Type.DEADLINE);
        assertEquals(Parser.createTask(task3).getName(), "3");
        try {
            Parser.createTask(taskError1);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeException.EMPTY_TODO);
        }
        try {
            Parser.createTask(taskError2);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeException.WRONG_EVENT);
        }
        try {
            Parser.createTask(taskError3);
        } catch (DateTimeParseException e) {
            assertEquals(e.getMessage(), "Text '2020' could not be parsed at index 4");
        }
    }

    @Test
    public void storageTest() {
        try {
            File file = new File("test");
            Storage s = new Storage();
            s.file = file;
            TaskList taskList = new TaskList(s);
            Todo task1 = new Todo("Hng");
            task1.setCompleted();
            Deadline task2 = new Deadline("hehe", "2020-12-12");
            assertEquals(task1.getCompleted(), taskList.get(0).getCompleted());
            assertEquals(task1.getName(), taskList.get(0).getName());
            assertEquals(task2.getType(), taskList.get(1).getType());
            assertEquals(task2.getDeadline(), ((Deadline)taskList.get(1)).getDeadline());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
