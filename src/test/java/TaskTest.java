import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private final String description = "description";

    @Test
    public void markAsDone_correctDoneStatusShown() throws TaskException {
        ToDo todo = new ToDo(description, false, null);
        assertEquals(todo.toString(), "[" + todo.taskType.getSymbol() + "]" + "[\u2718]" + " " + description);
        todo.markAsDone();
        assertEquals(todo.toString(), "[" + todo.taskType.getSymbol() + "]" + "[\u2713]" + " " + description);
    }

    @Test
    public void getIsOccuringOn_eventAgainstTwoDates() throws ParseException {
        Event event = new Event(description, new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-04"), false, null);
        assertTrue(event.isOccuringOn(new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-04")));
        assertFalse(event.isOccuringOn(new SimpleDateFormat("yyyy-MM-dd").parse("2020-04-04")));
    }

}
