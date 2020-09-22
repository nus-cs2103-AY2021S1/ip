import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void testToString() {
        LocalDate localDate = LocalDate.parse("25-08-2020",
                DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Deadline deadline = new Deadline("return book", localDate);
        assertEquals("[D][✘] return book (by: Aug 25 2020)" , deadline.toString());
    }
}
