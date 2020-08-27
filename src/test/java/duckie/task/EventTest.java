package duckie.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void event_inputCommand_eventString() {
        String date = "20 Aug 2020 07:30 PM";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");
        Task task = new Event("Dinner with friends", LocalDateTime.parse(date, formatter));
        assertEquals("[E][✘] Dinner with friends (at: Thu, Aug 20 2020 07:30 PM)", task.toString());
    }

    @Test
    public void eventMarkDone_inputCommand_eventString() {
        String date = "20 Aug 2020 07:30 PM";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");
        Task task = new Event("Dinner with friends", LocalDateTime.parse(date, formatter));
        task.checked();
        assertEquals("[E][✔] Dinner with friends (at: Thu, Aug 20 2020 07:30 PM)", task.toString());
    }
}
