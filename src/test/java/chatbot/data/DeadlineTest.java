package chatbot.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void deadlineBody_datePresent_success() {
        Deadline dl = Deadline.newDeadline("return book", LocalDate.parse("2020-08-25"));
        assertEquals(dl.getDescription(), "return book");
        assertEquals(dl.getDate().toString(), "2020-08-25");
    }

}
