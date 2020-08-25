import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTest {

    @Test
    public void addTest() {
        TaskList tasks = new TaskList();
        ToDo item = new ToDo("hi");
        tasks.add(item);
        assertEquals(1, tasks.size());
    }

    @Test
    public void getTest() {
        TaskList tasks = new TaskList();
        ToDo item = new ToDo("hi");
        tasks.add(item);
        assertEquals(item.toString(), tasks.get(0).toString());
    }

    @Test
    public void taskClassifyTest() throws DukeException {
        Event event = new Event("stay behind", "today");
        String desc = "event stay behind /at today";
        assertEquals(event.toString(),
                Parser.taskClassify(desc).toString());
    }


}
