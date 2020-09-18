import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    protected Task todo = new Todo("sleep");
    protected Task event = new Event("project meeting", "17/11/2015", "1532");

    public TaskListTest() throws DukeException {
    }

    @Test
    void taskArraySize_validInput_success() {
        TaskList todoList = new TaskList();
        todoList.addTask(todo);
        todoList.addTask(event);
        assertEquals(2, todoList.taskArraySize());
        todoList.deleteTask(0);
        todoList.deleteTask(0);
        assertEquals(0, todoList.taskArraySize());
    }

    @Test
    void getTask_validInput_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(todo);
        taskList.addTask(event);
        String toDoTask = taskList.get(0).toString();
        String eventTask = taskList.get(1).toString();
        assertEquals("[T][\u2718] sleep", toDoTask);
        assertEquals("[E][\u2718] project meeting (at: 17th of November 2015, 3:32pm)", eventTask);
    }
}
