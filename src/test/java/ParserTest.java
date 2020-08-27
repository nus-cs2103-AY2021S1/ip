import duke.*;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    void parseAdd() {
        Parser parser = new Parser();
        String in = "todo asdf";
        Task todo = new ToDo("asdf");
        Task res = null;
        try {
            res = parser.parseAdd(in);
        } catch (DukeException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(todo.toString(), res.toString());
    }
}
