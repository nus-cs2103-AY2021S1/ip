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
    void getList() {
    }
    @Test
    void addToList() {
        // clear text file first, store.clearFile() not working
        store.clearFile();
        listOfTask = store.retrieveStorage();
        list = new TaskList(listOfTask, store);
        String activity = "read book";
        ToDoTask newTask = new ToDoTask(activity, TaskSymbol.TODO);
        list.addToList(newTask);
        assertEquals(1,list.getList().size(), "Number of Lists after adding a task");
    }
    @Test
    void removeTask() {
        // Clear text file first, store.clearFile() not working
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
    void readList() {
    }
    @Test
    void setTaskDone() {
    }
    @Test
    void findTask() {
    }
}