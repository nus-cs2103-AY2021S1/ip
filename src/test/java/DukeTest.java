import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DukeTest {
    @Test
    public void taskList_addTask() throws DukeDuplicateException {
        TaskList list = new TaskList();
        list.addTask(new ToDos("placeholder", false));
        list.addTask(new Events("birthday", "12-12-2020 1200", true));
        assertEquals(list.getSize(), 2);
        assertEquals(list.getTask(1).toString(), "[T][✗]placeholder");
    }

    @Test
    public void taskList_addTask_deleteTask() throws DukeDuplicateException {
        TaskList list = new TaskList();
        list.addTask(new ToDos("do homework", false));
        list.addTask(new Deadline("CS2103 project", "26-08-2020 2359", false));
        list.addTask(new Events("Friend Outing", "27-08-2020 2000", false));
        list.deleteTask(3);
        assertEquals(list.getSize(), 2);
        assertEquals(list.getTask(2).toString(), "[D][✗]CS2103 project (by: 26-08-2020 2359)");
    }

    @Test
    public void taskList_addTask_saveString() throws DukeDuplicateException {
        TaskList list = new TaskList();
        list.addTask(new ToDos("do homework", false));
        list.addTask(new Deadline("CS2103 project", "26-08-2020 2359", false));
        list.addTask(new Events("Friend Outing", "27-08-2020 2000", false));
        assertEquals(list.getSize(), 3);
        assertEquals(list.getTask(3).toSaveString(), "E | 0 | Friend Outing | 27-08-2020 2000");
    }
}
