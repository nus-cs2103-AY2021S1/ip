import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventCommandTest {
    @Test
    void executeTest() throws WrongDateFormatException, InvalidTaskDescriptionException {
        UI dukeUI = new UI();
        TaskList tasks = new TaskList(new ArrayList<>(){});
        EventCommand dummyEventCmd = new EventCommand("event read book/2020-12-12");
        dummyEventCmd.execute(tasks, dukeUI);
        assertEquals("[E]" + "[\u2718]" + " read book" +
                " (at: 12 Dec 2020)", tasks.getTaskList().get(0).toString());
    }
}
