package duke.Tasks;

import duke.Ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void getListTest1() {
        TaskList list = new TaskList();
        list.addTodo(new Ui(), "item 1");
        list.addTodo(new Ui(), "item 2");
        list.addTodo(new Ui(), "item 3");

        assertEquals(3, list.getListOfTasks().size());
    }

    @Test
    public void getListTest2() {
        TaskList list = new TaskList();
        list.addTodo(new Ui(), "item 1");
        list.addTodo(new Ui(), "item 2");
        list.addTodo(new Ui(), "item 3");

        assertFalse( list.getListOfTasks().get(1) instanceof Deadline);
        assertFalse( list.getListOfTasks().get(1) instanceof Event);
        assertTrue( list.getListOfTasks().get(1) instanceof ToDo);
    }

    @Test
    public void taskDeleteTest() {
        TaskList list = new TaskList();
        list.addTodo(new Ui(), "item 1");
        list.addTodo(new Ui(), "item 2");
        list.addTodo(new Ui(), "item 3");
        list.taskDelete(2, new Ui());
        assertEquals(2, list.getListOfTasks().size());
    }
}
