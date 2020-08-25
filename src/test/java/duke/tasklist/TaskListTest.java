package duke.tasklist;

import duke.tasks.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testMarkTaskAsDone() {
        ArrayList<Task> lst = new ArrayList<>();
        TaskList taskList = new TaskList(lst);
        ToDo toDo = new ToDo("read book");
        taskList.addTask(toDo);
        taskList.markTaskAsDone(0);
        assertEquals(true, toDo.isDone);
    }

    @Test
    public void testAddTask() {
        ArrayList<Task> lst = new ArrayList<>();
        TaskList taskList = new TaskList(lst);
        ToDo toDo = new ToDo("read book");
        taskList.addTask(toDo);
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(toDo);
        assertEquals(expected, lst);
    }

    @Test
    public void testDeleteTask() {
        ArrayList<Task> lst = new ArrayList<>();
        TaskList taskList = new TaskList(lst);
        ToDo toDo = new ToDo("read book");
        taskList.addTask(toDo);
        taskList.deleteTask(0);
        ArrayList<Task> expected = new ArrayList<>();
        assertEquals(expected, lst);
    }
}
