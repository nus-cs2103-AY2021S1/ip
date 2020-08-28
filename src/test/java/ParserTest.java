import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParserTest {
    Parser parser = new Parser();
    @Test
    public void parserGetDetails_validTodoInput_trueReturned() {
        String[] details = parser.getDetails("todo event");
        String[] answers = {"todo",  " event", null};
        for (int i = 0; i < details.length; i ++) {
            assertEquals(answers[i],details[i]);
        }
    }
    @Test
    public void parserGetDetails_validDeadLineInput_trueReturned() {
        String[] details = parser.getDetails("deadline event /by 2012-02-2");
        String[] answers = {"deadline", " event", "2012-02-2"};
        for (int i = 0; i < details.length; i ++) {
            assertEquals(answers[i],details[i]);
        }
    }

    @Test
    public void parserExtractDetails_validDeadLineInput_trueReturned() {
        String[] details = parser.extractDetails(new String[]{"deadline", "event", "/by","2012-02-2"});
        String[] answers = {"deadline", " event", "2012-02-2"};
        for (int i = 0; i < details.length; i ++) {
            assertEquals(answers[i],details[i]);
        }
    }
}
