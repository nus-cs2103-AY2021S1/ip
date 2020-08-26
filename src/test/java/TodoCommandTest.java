import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {
    @Test
    public void testTodoCommand() throws Exception {
        TodoCommand d = new TodoCommand("Todo sleep");
        Ui ui = new Ui();
        TaskList list = new TaskList();
        Storage s = new Storage("data/tasks.txt");
        d.execute(list, ui, s);
        String expectedOutput = "[[T][✘] sleep]";
        assertEquals(expectedOutput, list.getList().toString());
    }
}
