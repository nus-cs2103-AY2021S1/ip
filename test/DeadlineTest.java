import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class DeadlineTest {
    @Test
    void testToString() {
        Deadline newDeadline = new Deadline("Play Maplestory ", LocalDate.parse("2019-10-25"));
        assertEquals("[D][✘] Play Maplestory (by: Oct 25 2019)", newDeadline.toString());
    }

    @Test
    void testFileTask() {
        Deadline newDeadline = new Deadline("Play Summoners War ", "Dec 15 2018", true);
        assertEquals("[D][✓] Play Summoners War (by: Dec 15 2018)", newDeadline.toString());
    }

    @Test
    void testAddTask() {
        Deadline newDeadline = new Deadline("Play Brawl Stars ", LocalDate.parse("2019-10-25"));
        assertEquals("[D][✘] Play Brawl Stars (by: Oct 25 2019)", newDeadline.toString());
    }
}