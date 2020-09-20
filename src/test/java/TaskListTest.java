import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import juke.TaskList;
import juke.task.Deadline;
import juke.task.Event;
import juke.task.TaskDate;
import juke.task.Todo;

public class TaskListTest {

    @Test
    public void testAddingTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Event event = new Event("eat eggs", TaskDate.parse("2020-09-17"));

        String expectedText = String.format("Alright matey, I've added this task:\n%s\nYou have 1 tasks in total.",
                event);
        String actualText = taskList.addToList(event);

        assertEquals(expectedText, actualText);
    }

    @Test
    public void testRemovingTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Event event = new Event("eat eggs", TaskDate.parse("2020-09-17"));
        taskList.addToList(event);

        String expectedText = String.format("Well, if you insist. I've removed:\n%s", event);
        String actualText = taskList.removeFromList(0);

        assertEquals(expectedText, actualText);
    }

    @Test
    public void testFindTaskWithMatchingTasks() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Event event = new Event("eat eggs", TaskDate.parse("2020-09-17"));
        Deadline deadline = new Deadline("cook eggs", TaskDate.parse("2020-09-17"));
        Todo todo = new Todo("wash dishes");
        taskList.addToList(event);
        taskList.addToList(deadline);
        taskList.addToList(todo);

        String expectedText = String.format("Here are the tasks you're trying to find.\n1. %s\n2. %s\n",
                event, deadline);
        String actualText = taskList.findTasks("eggs");

        assertEquals(expectedText, actualText);
    }

    @Test
    public void testFindTaskWithNoMatchingTasks() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Event event = new Event("eat eggs", TaskDate.parse("2020-09-17"));
        Deadline deadline = new Deadline("cook eggs", TaskDate.parse("2020-09-17"));
        Todo todo = new Todo("wash dishes");
        taskList.addToList(event);
        taskList.addToList(deadline);
        taskList.addToList(todo);

        String expectedText = "No match found :(";
        String actualText = taskList.findTasks("Madagascar");

        assertEquals(expectedText, actualText);
    }

    @Test
    public void testUpdateDescription() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Event event = new Event("eat eggs", TaskDate.parse("2020-09-17"));
        taskList.addToList(event);
        String newDescription = "eat eggs and toast";
        Event newEvent = new Event(newDescription, TaskDate.parse("2020-09-17"));

        String expectedText = String.format("I've changed the task description! The task now looks like this:\n%s",
                newEvent);
        String actualText = taskList.updateDescription(0, newDescription);

        assertEquals(expectedText, actualText);
    }

    @Test
    public void testUpdateDate() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Event event = new Event("eat eggs", TaskDate.parse("2020-09-17"));
        taskList.addToList(event);
        TaskDate newDate = TaskDate.parse("2020-12-12");
        Event newEvent = new Event("eat eggs", newDate);

        String expectedText = String.format("I've changed the task date! The task now looks like this:\n%s",
                newEvent);
        String actualText = taskList.updateDate(0, newDate);

        assertEquals(expectedText, actualText);
    }

    @Test
    public void testUpdateDescriptionAndDate() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Event event = new Event("eat eggs", TaskDate.parse("2020-09-17"));
        taskList.addToList(event);
        String newDescription = "eat eggs and toast";
        TaskDate newDate = TaskDate.parse("2020-12-12");
        Event newEvent = new Event(newDescription, newDate);

        String expectedText = String.format("I've changed the task description and date! "
                        + "The task now looks like this:\n%s", newEvent);
        String actualText = taskList.updateDescriptionAndDate(0, newDescription, newDate);

        assertEquals(expectedText, actualText);
    }
}
