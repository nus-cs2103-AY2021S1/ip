package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.todo.Task;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList tasks = new TaskList();
        String description = "borrow book";
        Task newTask = new Task(description);
        String output = tasks.addTask(newTask);
        String expected = Ui.ADD_MSG + "\n"
                + newTask + "\n"
                + "Now you have 1 tasks in the list.\n";
        assertEquals(expected, output);
    }

    @Test
    public void deleteTaskTest() {
        TaskList tasks = new TaskList();
        String description1 = "borrow book";
        Task task1 = new Task(description1);
        String description2 = "water plant";
        Task task2 = new Task(description2);
        String description3 = "walk dog";
        Task task3 = new Task(description3);
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);

        String output = tasks.deleteTask(1);
        String expected = Ui.DELETE_MSG + "\n"
                + " " + " "
                + task1 + "\n"
                + "Now you have 2 tasks in the list.\n";
        assertEquals(expected, output);
    }

    @Test
    public void searchKeywordTest() {
        TaskList tasks = new TaskList();
        String description1 = "borrow book";
        Task task1 = new Task(description1);
        String description2 = "water plant";
        Task task2 = new Task(description2);
        String description3 = "walk dog";
        Task task3 = new Task(description3);
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);

        String output = tasks.searchKeyword("book");
        String expected = "Here are the matching tasks in your list:\n"
                + "1." + task1 + "\n";
        assertEquals(expected, output);
    }
}
