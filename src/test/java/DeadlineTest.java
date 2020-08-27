import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void getStatusIcon() {
        Deadline deadline = new Deadline("return book /by 2020-12-31", false, LocalDate.parse("2020-12-31"));
        assertEquals("\u2718", deadline.getStatusIcon()) ;
    }

}