import duke.main.Command;
import duke.tools.Parser;
import duke.tools.Time;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class is to test the Parser class.
 * Mainly the commands of the Parser and
 * other related methods are tested.
 */
public class ParserTest {

    /**
     * Tests the "todo" command.
     */
    @Test
    public void runTodoTest() {
        Parser.run("todo reading");
        Parser.reloadTaskList();
        Task expected = new Todo("reading");
        boolean isExist = false;

        int size = Parser.getTaskList().getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.getTaskList().getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                isExist = true;
            }
        }

        Parser.run(Command.CLEAR.toString());
        assertTrue(isExist);
    }

    /**
     * Tests the "deadline" command.
     */
    @Test
    public void runDeadlineTest() {
        Parser.run("deadline eating /by 2020-08-30");
        Task expected = new Deadline("eating", new Time("2020-08-30").toString());
        boolean isExist = false;

        int size = Parser.getTaskList().getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.getTaskList().getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                isExist = true;
            }
        }

        Parser.run(Command.CLEAR.toString());
        assertTrue(isExist);
    }

    /**
     * Tests the "event" command.
     */
    @Test
    public void runEventTest() {
        Parser.run("event working /by 2020-08-30");
        Task expected = new Event("working", new Time("2020-08-30").toString());
        boolean isExist = false;

        int size = Parser.getTaskList().getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.getTaskList().getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                isExist = true;
            }
        }

        Parser.run(Command.CLEAR.toString());
        assertTrue(isExist);
    }

    /**
     * Tests the "delete" command.
     */
    @Test
    public void runDeleteTest() {
        Parser.run(Command.CLEAR.toString());
        Parser.run("event working /by 2020-08-30");
        Parser.run("delete 1");
        Task expected = new Event("working", new Time("2020-08-30").toString());
        boolean isDeleted = true;

        int size = Parser.getTaskList().getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.getTaskList().getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                isDeleted = false;
            }
        }

        assertTrue(isDeleted);
    }

    /**
     * Tests the "done" command.
     */
    @Test
    public void runDoneTest() {
        Parser.run(Command.CLEAR.toString());
        Parser.run("event working /by 2020-08-30");
        Parser.run("done 1");
        Task expected = new Event("working", new Time("2020-08-30").toString());
        expected.setDone();
        boolean isDone = false;

        int size = Parser.getTaskList().getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.getTaskList().getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                isDone = true;
            }
        }

        Parser.run(Command.CLEAR.toString());
        assertTrue(isDone);
    }

}