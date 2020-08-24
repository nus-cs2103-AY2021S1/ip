package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {
    String name;
    TaskType type;
    LocalDate date;

    @BeforeEach
    void setUp() {
        this.name = "test";
        this.type = TaskType.TODO;
        this.date = LocalDate.parse("1900-01-02");
    }

    @Test
    void getDate_datePresent_returnDate() {
        assertEquals(new Task(name, type, date).getDate().get(), date);
    }

    @Test
    void getDate_dateEmpty_returnEmpty() {
        assertEquals(new Task(name, type).getDate(), Optional.empty());
    }

    @Test
    void testToString() {
        assertEquals(new Task(name, type).toString(), "[T][ ] test");
    }

    @Test
    void setDone_done_showDone() {
        Task task = new Task(name, type);
        task.setDone(true);
        assertEquals(task.toString(), "[T][x] test");
    }

    @Test
    void setDone_notDone_showNotDone() {
        Task task = new Task(name, type);
        task.setDone(true);
        task.setDone(false);
        assertEquals(task.toString(), "[T][ ] test");
    }
}
