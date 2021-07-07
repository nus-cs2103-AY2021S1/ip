package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Implements testing for task functionality.
 */
public class TaskTest {
    private static final String SEPARATOR = System.getProperty("line.separator");
    private TaskList testTasks;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Tests create a new to-do task.
     */
    @Test
    public void testCreateToDo() {
        Task newToDo = new ToDo("this is a to-do", false);
        assertEquals("[T][✘] this is a to-do", newToDo.toString());
    }

    /**
     * Tests create a new deadline task.
     */
    @Test
    public void testCreateDeadline() {
        Task newDeadline = new Deadline("this is a deadline", false, LocalDate.parse("2020-01-01"));
        assertEquals("[D][✘] this is a deadline (by: Jan 1 2020)", newDeadline.toString());
    }

    /**
     * Tests create a new event task.
     */
    @Test
    public void testCreateEvent() {
        Task newEvent = new Event("this is an event", false, LocalDate.parse("2020-12-12"));
        assertEquals("[E][✘] this is an event (at: Dec 12 2020)", newEvent.toString());
    }

    /**
     * Tests get tasks from task list.
     */
    @Test
    public void testGetTasks() {
        ArrayList<Task> testTasks = new ArrayList<>();
        testTasks.add(new ToDo("description", false));
        testTasks.add(new ToDo("done", true));
        this.testTasks = new TaskList(testTasks);
        assertEquals(this.testTasks.getTasks(), testTasks);
    }

    /**
     * Tests add task to task list.
     */
    @Test
    public void testAddTask() {
        Ui ui = new Ui();
        PrintStream ps = new PrintStream(out);
        System.setOut(ps);
        testTasks = new TaskList();
        Task newTask = new ToDo("to be done", false);
        testTasks.addTask(newTask, ui);
        ui.printMessage(ui.getResponses());
        assertEquals(" Your task has been recorded." + SEPARATOR
                + "   " + newTask + SEPARATOR + " You have 1 tasks currently."
                + SEPARATOR, out.toString());
        System.setOut(originalOut);
    }

    /**
     * Tests delete task from task list.
     */
    @Test
    public void testDeleteTask() {
        Ui ui = new Ui();
        PrintStream ps = new PrintStream(out);
        testTasks = new TaskList();
        Task newTask = new ToDo("to be done", false);
        testTasks.addTask(newTask, ui);
        ui.getResponses();
        System.setOut(ps);
        testTasks.deleteTask(-1, ui);
        ui.printMessage(ui.getResponses());
        assertEquals(" Sorry I cannot find your specified task :("
                + SEPARATOR, out.toString());
        out.reset();
        testTasks.deleteTask(1, ui);
        ui.printMessage(ui.getResponses());
        assertEquals(" Okay, I will remove this task for you" + SEPARATOR
                + "   " + newTask + SEPARATOR
                + " You have 0 tasks currently." + SEPARATOR,
                out.toString());
        System.setOut(originalOut);
    }

    /**
     * Tests mark task as done.
     */
    @Test
    public void testMarkAsDone() {
        Ui ui = new Ui();
        PrintStream ps = new PrintStream(out);
        testTasks = new TaskList();
        Task newTask = new ToDo("to be done", false);
        testTasks.addTask(newTask, ui);
        ui.getResponses();
        System.setOut(ps);
        testTasks.markAsDone(-1, ui);
        ui.printMessage(ui.getResponses());
        assertEquals(" Sorry I cannot find your specified task :("
                + SEPARATOR, out.toString());
        out.reset();
        testTasks.markAsDone(1, ui);
        ui.printMessage(ui.getResponses());
        assertEquals(" Congratulations for finishing this task!" + SEPARATOR
                + " Let me mark this as done for you." + SEPARATOR
                + "   " + newTask + SEPARATOR, out.toString());
        System.setOut(originalOut);
    }

    /**
     * Tests list all tasks from the task list.
     */
    @Test
    public void testListTasks() {
        Ui ui = new Ui();
        PrintStream ps = new PrintStream(out);
        testTasks = new TaskList();
        System.setOut(ps);
        testTasks.listTasks(ui);
        ui.printMessage(ui.getResponses());
        assertEquals(" You've got no tasks now." + SEPARATOR
                + " If you want to get busy add more task." + SEPARATOR
                + " I'll remember them for you :)" + SEPARATOR, out.toString());
        Task newTask = new ToDo("to be done", false);
        testTasks.addTask(newTask, ui);
        ui.getResponses();
        out.reset();
        testTasks.listTasks(ui);
        ui.printMessage(ui.getResponses());
        assertEquals(" Let me list out all your tasks..." + SEPARATOR
                + " 1." + newTask + SEPARATOR, out.toString());
        System.setOut(originalOut);
    }

    /**
     * Tests find tasks from the task list.
     */
    @Test
    public void testFindTasks() {
        Ui ui = new Ui();
        testTasks = new TaskList();
        Task newTask = new ToDo("to be done", false);
        testTasks.addTask(newTask, ui);
        Task newTask2 = new ToDo("to be cleared", false);
        testTasks.addTask(newTask2, ui);
        ui.getResponses();
        ArrayList<Task> matchingTasks = testTasks.findTasks("be");
        assertEquals(testTasks.getTasks(), matchingTasks);
    }

    /**
     * Tests update task from the task list.
     */
    @Test
    public void testUpdateTask() {
        Ui ui = new Ui();
        testTasks = new TaskList();
        Task newTask = new ToDo("to be done", false);
        testTasks.addTask(newTask, ui);
        ui.getResponses();
        try {
            testTasks.updateTask(1, "description", "to be cleared", ui);
            assertEquals(" Your task have been updated" + SEPARATOR
                    + "   " + testTasks.getTasks().get(0), ui.getResponses());
        } catch (DukeException error) {
            System.out.println(error.toString());
        }
    }
}
