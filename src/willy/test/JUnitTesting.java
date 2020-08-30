package willy.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import main.java.TaskList;
import willy.store.TaskStore;
import willy.task.Task;
import willy.task.TaskSymbol;
import willy.task.ToDoTask;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JUnitTesting {
    TaskStore store = new TaskStore();
    ArrayList<Task> listOfTask = store.retrieveStorage();
    TaskList list = new TaskList(listOfTask, store);

    @Test
    public void addTaskToList_success() {
        String activity = "read book";
        ToDoTask newTask = new ToDoTask(activity, TaskSymbol.TODO);
        list.addToList(newTask);
        assertEquals(1,list.getList().size(), "Number of Lists after adding a task");
    }

    @Test
    public void removeTaskFromList_success() {
        String activity1 = "read book";
        String activity2 = "go Home";
        // Add 2 tasks
        list.addToList(new ToDoTask(activity1, TaskSymbol.TODO));
        list.addToList(new ToDoTask(activity2, TaskSymbol.TODO));
        // Remove 1 task
        list.removeTask(1);
        assertEquals(1, list.getList().size(), "Number of Tasks after adding 2 tasks and removing 1 task");
    }

}
