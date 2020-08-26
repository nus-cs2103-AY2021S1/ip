package duke.deadline;

import duke.Tasks.Deadline;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTester {
    @Test
    @DisplayName("Testing getDeadlineDate method in Deadline class")
    public void testGetDeadlineDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate testDate = LocalDate.parse("2020-08-26");
        String result = testDate.format(formatter);
        assertEquals(new Deadline("Do something", "2020-08-26").getDeadlineDate(), result);
    }

    @Test
    @DisplayName("Testing getOriginal method in Deadline class")
    public void testGetOriginal() {
        String testTask = "deadline dosomething /by 2020-08-26 1800";
        assertEquals(new Deadline("dosomething", "2020-08-26 1800").getOriginal(),
                testTask);
    }

}
