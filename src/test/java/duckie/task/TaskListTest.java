package duckie.task;

import duckie.exception.DuckieException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void displayList_NoListException() {
        TaskList tL = new TaskList();
        try {
            tL.displayList();
        } catch (DuckieException e) {
            assertEquals("\t" + "Quack. You have no tasks in the list currently.",
                    e.getMessage());
        }
    }

    @Test
    public void deleteTask_task_taskListSizeNumber() {
        Task todo = new Todo("borrow books");
        Task todo2 = new Todo("wash clothes");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(todo);
        tasks.add(todo2);
        TaskList tL = new TaskList(tasks);
        tL.deleteTask(1);
        assertEquals(1, tL.getTaskList().size());
    }

    @Test
    public void deleteAllTasks_task_taskListSize() {
        Task todo = new Todo("borrow books");
        Task todo2 = new Todo("wash clothes");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(todo);
        tasks.add(todo2);
        TaskList tL = new TaskList(tasks);
        tL.deleteAllTasks();
        assertEquals(0, tL.getTaskList().size());
    }

    @Test
    public void addTask_task_taskListSizeNumber() {
        Task todo = new Todo("borrow books");
        Task todo2 = new Todo("wash clothes");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(todo);
        TaskList tL = new TaskList(tasks);
        tL.addTask(todo2);
        assertEquals(2, tL.getTaskList().size());
        assertEquals(1, tL.getIndex(todo2));
    }

    @Test
    public void getIndex_task_integerIndexNumber() {
        Task todo = new Todo("borrow books");
        Task todo2 = new Todo("wash clothes");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(todo);
        tasks.add(todo2);
        TaskList tL = new TaskList(tasks);
        assertEquals(1, tL.getIndex(todo2));
    }
}
