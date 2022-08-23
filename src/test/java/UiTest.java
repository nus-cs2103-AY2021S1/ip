import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    private Ui ui = new Ui();

    @Test
    public void exitLine() {
        String line = "Bye. Hope to see you again soon!\n";
        assertEquals(line, ui.exitLine());
    }

    @Test
    public void helpLine() {
        String line = "Below are the list of commands:\n"
                + "list\n"
                + "find [keyword]\n"
                + "todo [taskname]\n"
                + "deadline [taskname] /by [yyyy-MM-dd HHmm]\n"
                + "event [taskname] /at [yyy-MM-dd HHmm]\n"
                + "delete [index or all]\n"
                + "bye\n";

        assertEquals(line, ui.helpLine());
    }
}
