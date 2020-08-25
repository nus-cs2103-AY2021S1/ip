import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {
    Task todoEg = new Todo("Housework");
    Task deadlineEg = new Deadline("Phone Bills", "2020-08-08 13:00");
    Task eventEg = new Event("Meeting", "2020-08-08");

    @Test
    public void todoTest() {
        assertEquals("Housework", todoEg.getDescription());
        assertFalse(todoEg.isDone);
    }

    @Test
    public void deadlineTest() {
        assertEquals("Phone Bills", deadlineEg.getDescription());
        assertFalse(todoEg.isDone);
        assertEquals("2020-08-08", ((Deadline) deadlineEg).getDate().toString());
        assertEquals("13:00", ((Deadline) deadlineEg).getTime().toString());
    }

    @Test
    public void eventTest() {
        assertEquals("Meeting", eventEg.getDescription());
        assertFalse(todoEg.isDone);
        assertEquals("2020-08-08", ((Event) eventEg).getAt().toString());
    }

    @Test
    public void markAsDoneTest() {
        todoEg.markAsDone();
        assertTrue(todoEg.isDone);
    }

    @Test
    public void parserDeadlineTest() {
        String str = "deadline Phone Bills /by 2020-08-08 13:00";
        String[] arr = str.split(" ", 2);
        assertEquals("Phone Bills", Parser.parse(arr, 1)[0]);
    }

    @Test
    public void parserEventTest() {
        String str = "event Meeting /at 2020-08-08";
        String[] arr = str.split(" ", 2);
        assertEquals("Meeting", Parser.parse(arr, 2)[0]);
    }
}
