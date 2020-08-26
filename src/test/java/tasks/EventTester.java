package tasks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTester {

    @Test
    @DisplayName("Testing getBy method in Event class")
    public void testGetByTiming() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime testDate = LocalDateTime.parse("2020-02-02 12:12", formatter);
        assertEquals(new Event("test", testDate).getBy(), "2020-02-02 12:12");
    }

    @Test
    @DisplayName("Testing toString method in Event class")
    public void testToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime testDate = LocalDateTime.parse("2020-12-12 12:12", formatter);
        assertEquals(new Event("read book", testDate).toString(), "[E][✘] read book (at: Dec 12 2020 12:12)");
    }
}
