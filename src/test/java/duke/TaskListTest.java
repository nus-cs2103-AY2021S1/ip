package duke;

import duke.exception.DukeException;
import duke.exception.DuplicateTaskException;
import duke.exception.InvalidIndexException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void addToDoTest() {
        try {

            TaskList taskList = new TaskList();
            taskList.addToDo("sleep");

            ArrayList<Task> list = new ArrayList<>();
            list.add(new ToDo("sleep"));
            TaskList taskListTemplate = new TaskList(list);

            assertEquals(taskList, taskListTemplate);

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addEventTest() {
        try {

            TaskList taskList = new TaskList();
            taskList.addEvent("bfast /at 10:00");

            ArrayList<Task> list = new ArrayList<>();
            list.add(new Event("bfast", LocalDateTime.of(LocalDate.now(),
                    LocalTime.parse("10:00"))));

            TaskList taskListTemplate = new TaskList(list);

            assertEquals(taskList, taskListTemplate);

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addDeadlineTest() {
        try {

            TaskList taskList = new TaskList();
            taskList.addDeadline("project /by 2020-08-27");

            ArrayList<Task> list = new ArrayList<>();
            list.add(new Deadline("project",
                    LocalDateTime.of(LocalDate.parse("2020-08-27"), LocalTime.parse("23:59"))));

            TaskList taskListTemplate = new TaskList(list);

            assertEquals(taskList, taskListTemplate);

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void markDoneTest() {
        try {

            ArrayList<Task> list = new ArrayList<>();
            list.add(new ToDo("sleep"));
            TaskList taskList = new TaskList(list);
            taskList.markDone(0);

            ArrayList<Task> listTemplate = new ArrayList<>();
            listTemplate.add(new ToDo("sleep").markDone());
            TaskList taskListTemplate = new TaskList(listTemplate);

            assertEquals(taskList, taskListTemplate);

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deleteTaskTest() {
        try {

            ArrayList<Task> list = new ArrayList<>();
            list.add(new ToDo("sleep"));
            TaskList taskList = new TaskList(list);
            taskList.deleteTask(0);

            TaskList taskListTemplate = new TaskList();

            assertEquals(taskList, taskListTemplate);

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void printListTest1() {
        ArrayList<Task> list = new ArrayList<>();

        list.add(new ToDo("sleep"));
        list.add(new Event("bfast", LocalDateTime.of(LocalDate.now(),
                LocalTime.parse("10:00"))));
        list.add(new Deadline("project",
                LocalDateTime.of(LocalDate.parse("2020-08-27"), LocalTime.MAX)));

        TaskList taskList = new TaskList(list);

        assertDoesNotThrow(() -> {
            taskList.processList("list");
        });
    }

    @Test
    public void printListTest2() {
        ArrayList<Task> list = new ArrayList<>();

        list.add(new ToDo("sleep"));
        list.add(new Event("bfast", LocalDateTime.of(LocalDate.now(),
                LocalTime.parse("10:00"))));
        list.add(new Deadline("project",
                LocalDateTime.of(LocalDate.parse("2020-08-27"), LocalTime.MAX)));

        TaskList taskList = new TaskList(list);

        assertDoesNotThrow(() -> {
            taskList.processList("list 2020-08-27");
        });
    }

    @Test
    public void duplicateTaskTest() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new ToDo("sleep"));
        TaskList taskList = new TaskList(list);

        assertThrows(DuplicateTaskException.class, () -> {
            taskList.addToDo("sleep");
        });
    }

    @Test
    public void invalidIndexTest() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new ToDo("sleep"));
        TaskList taskList = new TaskList(list);

        assertThrows(InvalidIndexException.class, () -> {
            taskList.deleteTask(2);
        });
    }
}
