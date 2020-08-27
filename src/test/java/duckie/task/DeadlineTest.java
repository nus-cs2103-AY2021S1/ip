package duckie.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadline_inputCommand_deadlineString() {
        String date = "24 Aug 2020";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        Task task = new Deadline("Homework Quiz", LocalDate.parse(date, formatter));
        assertEquals("[D][✘] Homework Quiz (by: Mon, Aug 24 2020)", task.toString());
    }

    @Test
    public void deadlineMarkDone_inputCommand_deadlineString() {
        String date = "24 Aug 2020";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        Task task = new Deadline("Homework Quiz", LocalDate.parse(date, formatter));
        task.checked();
        assertEquals("[D][✔] Homework Quiz (by: Mon, Aug 24 2020)", task.toString());
    }
}