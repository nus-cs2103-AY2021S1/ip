import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {


    @Test
    void getNoOfTasks() {
        String tasks = "1.[T][✓] read book";
        TaskList list = new TaskList(tasks);
        assertEquals(1,list.getNoOfTasks());
    }

    @Test
    void addTask() {
        String tasks = "1.[T][✓] read book";
        TaskList list = new TaskList(tasks);
        Task t = new Task("do homework", false, "T");
        list.addTask(t);
        assertEquals(t, list.list.get(1));
    }

    @Test
    void deleteTask() {
        String tasks = "1.[T][✓] read book";
        TaskList list = new TaskList(tasks);
        Task t = list.list.get(0);
        list.deleteTask(t);
        assertEquals(true, list.list.isEmpty());
    }
}