import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    @Test
    public void testParseDeadLine() {
        UI ui = new UI();
        TaskList tasks = new TaskList();
        String respond = "added: [D][" + "\u2718" + "] finish homework (by: May 12 2020)";
        try {
            assertEquals(respond, new Parser().uiResponse(tasks,ui,"deadline finish homework /by 2020-05-12"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testParseEvent() {
        UI ui = new UI();
        TaskList tasks = new TaskList();
        String respond = "added: [E][" + "\u2718" + "] finish homework (at: 12 May 2020)";
        try {
            assertEquals(respond, new Parser().uiResponse(tasks,ui,"event finish homework /at 2020-05-12"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}