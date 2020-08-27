import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTaskListTest {
    TaskList taskList = new TaskList();

    @Test
    public void generateEmptyTaskListTest() {
        assertEquals(true, taskList.isEmpty());
    }

    @Test
    public void addTasksTest() {
        taskList.addTask(new Todo("Finish Drawing", TaskType.TODO));
        taskList.addTask(new Deadline("Essay ", "11/10/2020 1800", TaskType.DEADLINE));
        taskList.addTask(new Event("Meeting ", "20/06/2020 1400-1600", TaskType.EVENT));
        String todo = "[Todo][✘]Finish Drawing";
        String deadline = "[Deadline][✘]Essay (by: 2020-10-11 18:00)";
        String event = "[Event][✘]Meeting (at: 2020-06-20 14:00-16:00)";

        assertEquals(todo, taskList.getTask(0).toString());
        assertEquals(deadline, taskList.getTask(1).toString());
        assertEquals(event, taskList.getTask(2).toString());
    }

}