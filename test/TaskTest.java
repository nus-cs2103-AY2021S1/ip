import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void getStatusIcon() {
        Task task = new Task("Watch Tokyo Ravens", "T");
        assertEquals("\u2718", task.getStatusIcon());
    }

    @Test
    void getDescription() {
        Task task = new Task("Watch Tokyo Ravens", "T");
        assertEquals("Watch Tokyo Ravens", task.getDescription());
    }

    @Test
    void getType() {
        Task task = new Task("Watch D-gray man", "D");
        assertEquals("D", task.getType());
    }

    @Test
    void testToString() {
        Task task = new Task("Watch Enen no Shobotai", "E");
        assertEquals("[E][✘] Watch Enen no Shobotai", task.toString());
    }
}