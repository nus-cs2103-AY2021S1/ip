package chatbot.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    private final Deadline dl = Deadline.newDeadline("return book", LocalDate.parse("2020-08-25"));

    @Test
    public void factoryMethod_validArguments_correctAttributes() {
        assertEquals(dl.getDescription(), "return book");
        assertEquals(dl.getDate().toString(), "2020-08-25");
        assertEquals(dl.getStatus(), "0");
        assertEquals(dl.getType(), "D");
    }

    @Test
    public void markDone_correctStatus() {
        assertEquals(dl.markDone().getStatus(), "1");
    }

}
