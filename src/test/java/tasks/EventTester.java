package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.tasks.Event;

public class EventTester {

    /**
     * Testing getByTiming in event class.
     */
    @Test
    @DisplayName("Testing getBy method in Event class")
    public void testGetByTiming() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime testDate = LocalDateTime.parse("2020-02-02 12:12", formatter);
        Assertions.assertEquals(new Event("test", testDate).getBy(), "2020-02-02 12:12");
    }

    /**
     * Testing toString in event class.
     */
    @Test
    @DisplayName("Testing toString method in Event class")
    public void testToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime testDate = LocalDateTime.parse("2020-12-12 12:12", formatter);
        assertEquals(new Event("read book", testDate).toString(),
                "[E][✘] read book (at: Dec 12 2020 12:12)");
    }
}
