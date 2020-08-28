import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DogeTest {

    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void uiTest() {
        Ui ui = new Ui();
        String testString = "--------------------------------------" + "\n"
                + "Added to list : " + "printable message" + "\n"
                + "--------------------------------------";
    }

    @Test
    public void taskTest() {
        Task task = new Task("Cycling", TaskType.E);
        task.markAsDone();
        assertEquals("\u2713", task.getTaskStatusIcon());
    }

    @Test
    public void taskListTest() {
        Task task = new Task("Cycling", TaskType.E);
        TaskList.addToList(task);
        assertEquals(task, TaskList.TO_DO_LIST.get(0));
    }

    @Test
    public void  parserTest() {
        boolean isAddCommand = false;
        try {
            Command addCommand = Parser.manage("todo feed doge");
            isAddCommand = addCommand instanceof AddCommand;
        }
        catch (DukeException e) {
        }
        assertEquals(true, isAddCommand);
    }
}
