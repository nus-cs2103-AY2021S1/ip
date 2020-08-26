import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {
    @Test
    public void testDoneCommand() throws Exception {
        DoneCommand d = new DoneCommand("done 1");
        Ui ui = new Ui();
        TaskList list = new TaskList();
        list.addTask(new Todo("sleep"));
        Storage s = new Storage("data/tasks.txt");
        d.execute(list, ui, s);
        String expectedOutput = "[[T][✓] sleep]";
        assertEquals(expectedOutput, list.getList().toString());
    }
}
