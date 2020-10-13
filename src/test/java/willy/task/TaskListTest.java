package willy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import willy.store.TaskStore;

class TaskListTest {
    private TaskStore store = new TaskStore();
    private ArrayList<Task> listOfTask;
    private TaskList list;

    @Test
    // Check: After adding one task, the number of tasks increase by one.
    void addToList_addOneTask_success() {
        store.clearFile();
        listOfTask = store.retrieveStorage();
        list = new TaskList(listOfTask, store);
        String activity = "read book";
        TodoTask newTask = new TodoTask(activity, TaskSymbol.TODO);
        list.addToList(newTask);
        assertEquals(1, list.getList().size(), "Number of Lists after adding a task");
    }

    @Test
    // Check: After removing one task, the number of tasks decrease by one.
    void removeTask_removeOneTask_success() {
        store.clearFile();
        listOfTask = store.retrieveStorage();
        list = new TaskList(listOfTask, store);
        String activity1 = "read book";
        String activity2 = "go Home";
        // Add 2 tasks
        list.addToList(new TodoTask(activity1, TaskSymbol.TODO));
        list.addToList(new TodoTask(activity2, TaskSymbol.TODO));
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
        list.addToList(new TodoTask(activity1, TaskSymbol.TODO));
        list.setTaskDone(1);
        assertTrue(list.getList().get(0).isDone());
    }

    @Test
    void findTask_taskExists_success() {
        store.clearFile();
        listOfTask = store.retrieveStorage();
        list = new TaskList(listOfTask, store);
        String activity1 = "read book";
        String activity2 = "go Home";
        // Add 2 tasks
        list.addToList(new TodoTask(activity1, TaskSymbol.TODO));
        list.addToList(new TodoTask(activity2, TaskSymbol.TODO));
        list.findTask("read");
        assertEquals(1, list.getKeyList().size());
    }

    @Test
    void findTask_taskDoesNotExists_success() {
        store.clearFile();
        listOfTask = store.retrieveStorage();
        list = new TaskList(listOfTask, store);
        String activity1 = "read book";
        String activity2 = "go Home";
        // Add 2 tasks
        list.addToList(new TodoTask(activity1, TaskSymbol.TODO));
        list.addToList(new TodoTask(activity2, TaskSymbol.TODO));
        list.findTask("library");
        assertEquals(0, list.getKeyList().size());
    }
}
