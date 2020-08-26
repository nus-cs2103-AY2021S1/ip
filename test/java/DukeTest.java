import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void taskList_addTask() {
        TaskList list = new TaskList();
        list.addTask(new ToDos("placeholder", false));
        list.addTask(new Events("birthday", "12-12-2020 1200", true));
        assertEquals(list.size(), 2);
        assertEquals(list.getTask(1).toString(), "[T][✗]placeholder");
    }

    @Test
    public void taskList_addTask_deleteTask() {
        TaskList list = new TaskList();
        list.addTask(new ToDos("do homework", false));
        list.addTask(new Deadline("CS2103 project", "26-08-2020 2359", false));
        list.addTask(new Events("Friend Outing", "27-08-2020 2000", false));
        list.deleteTask(3);
        assertEquals(list.size(), 2);
        assertEquals(list.getTask(2).toString(), "[D][✗]CS2103 project (by: 26-08-2020 2359)");
    }

    @Test
    public void taskList_addTask_saveString() {
        TaskList list = new TaskList();
        list.addTask(new ToDos("do homework", false));
        list.addTask(new Deadline("CS2103 project", "26-08-2020 2359", false));
        list.addTask(new Events("Friend Outing", "27-08-2020 2000", false));
        assertEquals(list.size(), 3);
        assertEquals(list.getTask(3).toSaveString(), "E | 0 | Friend Outing | 27-08-2020 2000");
    }
}
