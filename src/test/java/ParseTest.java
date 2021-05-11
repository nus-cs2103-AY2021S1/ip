import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.commands.AddCommand;
import duke.task.Event;
import duke.task.Priority;

public class ParseTest {
    @Test
    public void parserSingleEventTest() {
        Event event = new Event("Cycling on WEDNESDAY 2 DECEMBER 2020 ", Priority.MEDIUM);
        AddCommand eventAdd = new AddCommand(event);
        AddCommand result = new AddCommand(new Event("Cycling", Priority.LOW));
        try {
            result = (AddCommand) Parser.manage("event Cycling /on 2020-12-02 -PL2");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(result, eventAdd);
    }

    /*@Test
    public void parserSingleEventTest() {
        Event event1 = new Event("Cycling", Priority.MEDIUM);
        Event event2 = new Event("Cycling", Priority.MEDIUM);
        Event[] eventArr1 = {event1};
        Event[] eventArr2 = {event2};
        assertEquals(eventArr1, eventArr2);
    }*/

    @Test
    public void checkCommaTest() {
        String input = "doge,feed";
        ArrayList<String> commaSeperated = Parser.checkCommas(input);
        assertEquals("doge", commaSeperated.get(0));
        assertEquals("feed", commaSeperated.get(1));
    }

}
