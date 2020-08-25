package duck;

import duck.exception.DuckException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseOptionCorrectly() {
        String[] inputs = {"bye", "list", "done 2","deadline abc /by 2020-12-12",
                "todo something", "event abc /at 2020-12-12", "due", ":("};
        Option[] expected = {Option.BYE, Option.LIST, Option.DONE, Option.DEADLINE, Option.TODO,
                Option.EVENT, Option.DUE, Option.UNRECOGNIZED};
        for (int i = 0; i < inputs.length; i++) {
            assertEquals(expected[i], Parser.parseOption(inputs[i]));
        }
    }

    @Test
    public void parseDescriptionCorrectly() {
        String[] inputs = {" read book", " finish cs /by 2020-12-12",
                " what event /at 2020-12-12"};
        String[] expected = {"read book", "finish cs", "what event"};
        for (int i = 0; i < inputs.length; i++) {
            try {
                assertEquals(expected[i], Parser.parseDescription(inputs[i]));
            } catch (DuckException e) {
                fail();
            }
        }
    }

    @Test
    public void parseDateCorrectly() {
        String[] inputs = {" finish cs /by 2020-12-12",
                " what event /at 2020-12-12"};
        LocalDate expected = LocalDate.parse("2020-12-12");
        for (int i = 0; i < inputs.length; i++) {
            try {
                assertEquals(expected, Parser.parseDate(inputs[i]));
            } catch (DuckException e) {
                fail();
            }
        }
    }

    @Test
    public void parseTaskNumberCorrectly() {
        String[] inputs = {"done 3", "delete 5"};
        int[] expected = {3,5};
        for (int i = 0; i < inputs.length; i++) {
            try {
                assertEquals(expected[i], Parser.parseTaskNumber(inputs[i]));
            } catch (DuckException e) {
                fail();
            }
        }
    }
}
