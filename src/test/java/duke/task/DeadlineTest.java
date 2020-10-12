package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.Deadline;

public class DeadlineTest {
    private String[] split = "deadline return book /by 2019-10-15".substring(9).split(" /by ");
    private String deadlineDesc = split[0];
    private LocalDate deadline = LocalDate.parse(split[1]);

    @Test
    public void deadlineConstructor_deadlineWithNameAndDate() {
        assertEquals("[D][\u2718] return book (by: Oct 15 2019)",
                new Deadline(deadlineDesc, deadline, false).toString());
    }

}
