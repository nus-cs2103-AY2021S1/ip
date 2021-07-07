package duke.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

public class EventTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void test_toDataFileFormat_notDone(){
        String taskDescription = "Test";
        DukeDateTime dateTime = new DukeDateTime(LocalDateTime.parse("2020-10-24T16:00"), true);
        Event testEvent = new Event(taskDescription, dateTime);
        String expectedOutput = "E | 0 | " + taskDescription + " | 24 Oct 2020 4:00 PM";
        assertEquals(expectedOutput, testEvent.toDataFileFormat());
    }

    @Test
    public void test_toDataFileFormat_done(){
        String taskDescription = "Test";
        DukeDateTime dateTime = new DukeDateTime(LocalDateTime.parse("2020-10-24T16:00"), true);
        Event testEvent = new Event(taskDescription, dateTime);
        testEvent.markTaskAsDone();
        String expectedOutput = "E | 1 | " + taskDescription + " | 24 Oct 2020 4:00 PM";
        assertEquals(expectedOutput, testEvent.toDataFileFormat());
    }

    @Test
    public void test_toString_notDone(){
        String taskDescription = "Test";
        DukeDateTime dateTime = new DukeDateTime(LocalDateTime.parse("2020-10-24T16:00"), true);
        Event testEvent = new Event(taskDescription, dateTime);
        String expectedOutput = "[E][\u2718] " + taskDescription + " (at: 24 Oct 2020 4:00 PM)";
        assertEquals(expectedOutput, testEvent.toString());
    }

    @Test
    public void test_toString_done(){
        String taskDescription = "Test";
        DukeDateTime dateTime = new DukeDateTime(LocalDateTime.parse("2020-10-24T16:00"), true);
        Event testEvent = new Event(taskDescription, dateTime);
        testEvent.markTaskAsDone();
        String expectedOutput = "[E][\u2713] " + taskDescription + " (at: 24 Oct 2020 4:00 PM)";
        assertEquals(expectedOutput, testEvent.toString());
    }
}
