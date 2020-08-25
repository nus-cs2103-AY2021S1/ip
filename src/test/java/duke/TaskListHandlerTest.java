package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListHandlerTest {

    @Test
    void getTaskList() {
        TaskListHandler handler = new TaskListHandler(new ArrayList<>());
        handler.getTaskList().add(new Todo("assignment"));

        ArrayList<Task> newTaskList = new ArrayList<>();
        newTaskList.add(new Todo("assignment"));
        assertEquals(handler.getTaskList(), newTaskList);
        System.out.println("Passed: getTaskListTest!");
    }

    @Test
    void addToList() {
        TaskListHandler handler = new TaskListHandler(new ArrayList<>());
        handler.addToList(new Event("mega sale", "12pm"));
        ArrayList<Task> newTaskList = new ArrayList<>();
        newTaskList.add(new Event("mega sale", "12pm"));
        assertEquals(handler.getTaskList(), newTaskList);
        System.out.println("Passed: addToListTest!");
    }

    @Test
    void clearList() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        TaskListHandler handler = new TaskListHandler(new ArrayList<>());
        handler.addToList(new Event("mega sale", "12pm"));
        System.setOut(new PrintStream(outContent));
        handler.clearList();
        assertEquals(handler.getTaskList(), new ArrayList<Task>());
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println("Passed: clearListTest!");
    }

    @Test
    void indent() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TaskListHandler.indent(5);
        String expectedOutput  = "                    ";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println("Passed: indentTest!");
    }

    @Test
    void printList() throws DukeException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TaskListHandler handler = new TaskListHandler(new ArrayList<>());
        handler.addToList(new Event("mega sale", "12pm"));
        handler.addToList(new Todo("assignment"));
        handler.addToList(new Deadline("submission", "9pm"));
        handler.printList();
        String expectedOutput  = "    Here are the tasks in your list:" + System.getProperty("line.separator") +
                "        1. [E][✘] mega sale (at: 12pm)" + System.getProperty("line.separator") +
                "        2. [T][✘] assignment" + System.getProperty("line.separator") +
                "        3. [D][✘] submission (by: 9pm)" + System.getProperty("line.separator") +
                "    You have 3 task(s) in the list" + System.getProperty("line.separator");
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println("Passed: printListTest!");
    }
}