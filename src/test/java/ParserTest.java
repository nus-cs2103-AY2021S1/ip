import duke.command.CommandString;
import duke.exception.DukeException;
import duke.tools.Parser;
import duke.tools.Time;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the Parser class.
 * Mainly the commands of the Parser and
 * other related methods are tested.
 */
public class ParserTest {

    /**
     * Tests the "todo" command.
     */
    @Test
    public void runTodoTest() throws DukeException {
        Parser.run(CommandString.CLEAR).process();
        Parser.run("todo reading").process();
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

        Parser.run(CommandString.CLEAR).process();
        assertTrue(isExist);
    }

    /**
     * Tests the "deadline" command.
     */
    @Test
    public void runDeadlineTest() throws DukeException {
        Parser.run("deadline eating /by 2020-08-30").process();
        Task expected = new Deadline("eating", new Time("2020-08-30").toString());
        boolean isExist = false;

        int size = Parser.getTaskList().getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.getTaskList().getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                isExist = true;
            }
        }

        Parser.run(CommandString.CLEAR).process();
        assertTrue(isExist);
    }

    /**
     * Tests the "event" command.
     */
    @Test
    public void runEventTest() throws DukeException {
        Parser.run("event working /by 2020-08-30").process();
        Task expected = new Event("working", new Time("2020-08-30").toString());
        boolean isExist = false;

        int size = Parser.getTaskList().getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.getTaskList().getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                isExist = true;
            }
        }

        Parser.run(CommandString.CLEAR).process();
        assertTrue(isExist);
    }

    /**
     * Tests the "delete" command.
     */
    @Test
    public void runDeleteTest() throws DukeException {
        Parser.run(CommandString.CLEAR).process();
        Parser.run("event working /by 2020-08-30").process();
        Parser.run("delete 1").process();
        boolean isDeleted = false;

        Parser.reloadTaskList();
        int size = Parser.getTaskList().getTaskList().size();
        isDeleted = size == 0;

        assertTrue(isDeleted);
    }

    /**
     * Tests the "done" command.
     */
    @Test
    public void runDoneTest() throws DukeException {
        Parser.run(CommandString.CLEAR).process();
        Parser.run("event working /on 2020-08-30").process();
        Parser.run("done 1").process();
        boolean isDone = false;

        Parser.reloadTaskList();
        Task task = Parser.getTaskList().getTaskList().get(0);
        isDone = task.isDone();

        Parser.run(CommandString.CLEAR).process();
        assertTrue(isDone);
    }

}