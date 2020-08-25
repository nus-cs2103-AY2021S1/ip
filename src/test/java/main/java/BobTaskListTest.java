package main.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BobTaskListTest {
    @Test
    public void isEmpty_emptyTaskList_true() {
        TaskList tasks = new TaskList();
        Assertions.assertEquals(true, tasks.isEmpty());
    }

    @Test
    public void get_emptyTaskList_exceptionThrown(){
        TaskList tasks = new TaskList();
        try {
            Assertions.assertEquals(tasks.get(1), new Task("empty"));
            Assertions.fail();
        } catch (BobIndexOutOfBoundsException e) {
            Assertions.assertEquals("There are no tasks on the list with the provided index. Please check the list and try again!", e.getMessage());
        }
    }

    @Test
    public void size_TaskListWith5Tasks_success() {
        TaskList tasks = new TaskList();
        for (int i = 0; i < 5 ; i++) {
            tasks.add(new Todo("empty"));
        }
        Assertions.assertEquals(5,tasks.size());
    }

    @Test
    public void addAndDelete_TaskListWith3Tasks_success() {
        TaskList tasks = new TaskList();
        for (int i = 0; i < 5 ; i++) {
            tasks.add(new Todo("empty"));
        }
        try {
            tasks.delete(1);
            tasks.delete(2);
        } catch (BobIndexOutOfBoundsException e) {
        }
        Assertions.assertEquals(3,tasks.size());
    }
}
