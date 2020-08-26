import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void testDeleteCommand() throws Exception {
        DeleteCommand d = new DeleteCommand("delete 2");
        Ui ui = new Ui();
        TaskList list = new TaskList();
        list.addTask(new Todo("sleep"));
        list.addTask(new Todo("eat"));
        Storage s = new Storage("data/tasks.txt");
        d.execute(list, ui, s);
        String expectedOutput = "[[T][✘] sleep]";
        assertEquals(expectedOutput, list.getList().toString());
    }
}
