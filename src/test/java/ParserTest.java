package test.java;

import Duke.main.Parser;
import Duke.main.Time;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class is to test the Parser class.
 * Mainly the commands of the Parser and
 * other related methods are tested.
 */
public class ParserTest {
    //Warning: clear the strings in data/Duke.txt before testing.

    /**
     * Tests the "todo" command.
     */
    @Test
    public void runTodoTest() {
        Parser.run("todo reading");
        Parser.reloadTaskList();
        Task expected = new Todo("reading");
        boolean isExist = false;

        int index = 1;
        int size = Parser.taskList.getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.taskList.getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                index = i;
                System.out.println(i);
                System.out.println(index);
                isExist = true;
            }
        }

        Parser.run(String.format("delete %d", index + 1));
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

        int index = 1;
        int size = Parser.taskList.getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.taskList.getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                isExist = true;
                index = i;
            }
        }

        Parser.run(String.format("delete %d", index + 1));
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

        int index = 1;
        int size = Parser.taskList.getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.taskList.getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                isExist = true;
                index = i;
            }
        }

        Parser.run(String.format("delete %d", index + 1));
        assertTrue(isExist);
    }

    /**
     * Tests the "delete" command.
     */
    @Test
    public void runDeleteTest() {
        Parser.run("event working /by 2020-08-30");
        Parser.run("delete 1");
        Task expected = new Event("working", new Time("2020-08-30").toString());
        boolean isDeleted = true;

        int size = Parser.taskList.getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.taskList.getTaskList().get(i);
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
        Parser.run("event working /by 2020-08-30");
        Parser.run("done 1");
        Task expected = new Event("working", new Time("2020-08-30").toString());
        expected.setDone();
        boolean isDone = false;

        int index = 1;
        int size = Parser.taskList.getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.taskList.getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                isDone = true;
                index = i;
            }
        }

        Parser.run(String.format("delete %d", index + 1));
        assertTrue(isDone);
    }

}