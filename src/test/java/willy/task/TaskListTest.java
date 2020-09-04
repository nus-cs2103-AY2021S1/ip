package willy.task;

import org.junit.jupiter.api.Test;
import willy.store.TaskStore;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class TaskListTest {
    TaskStore store = new TaskStore();
    ArrayList<Task> listOfTask;
    TaskList list;

    @Test
    // Check: After adding one task, the number of tasks increase by one.
    void addToList() {
        store.clearFile();
        listOfTask = store.retrieveStorage();
        list = new TaskList(listOfTask, store);
        String activity = "read book";
        ToDoTask newTask = new ToDoTask(activity, TaskSymbol.TODO);
        list.addToList(newTask);
        assertEquals(1,list.getList().size(), "Number of Lists after adding a task");
    }

    @Test
    // Check: After removing one task, the number of tasks decrease by one.
    void removeTask() {
        store.clearFile();
        listOfTask = store.retrieveStorage();
        list = new TaskList(listOfTask, store);
        String activity1 = "read book";
        String activity2 = "go Home";
        // Add 2 tasks
        list.addToList(new ToDoTask(activity1, TaskSymbol.TODO));
        list.addToList(new ToDoTask(activity2, TaskSymbol.TODO));
        // Remove 1 task
        list.removeTask(1);
        assertEquals(1, list.getList().size(), "Number of Tasks after adding 2 tasks and removing 1 task");
    }

    @Test
    void setTaskDone() {
        store.clearFile();
        listOfTask = store.retrieveStorage();
        list = new TaskList(listOfTask, store);
        String activity1 = "read book";
        list.addToList(new ToDoTask(activity1, TaskSymbol.TODO));
        list.setTaskDone(1);
        assertTrue(list.getList().get(0).isDone());
    }

    @Test
    void findTask() {
        store.clearFile();
        listOfTask = store.retrieveStorage();
        list = new TaskList(listOfTask, store);
        String activity1 = "read book";
        String activity2 = "go Home";
        // Add 2 tasks
        list.addToList(new ToDoTask(activity1, TaskSymbol.TODO));
        list.addToList(new ToDoTask(activity2, TaskSymbol.TODO));
        list.findTask("read");
        assertEquals(1, list.getKeyList().size());
    }
}