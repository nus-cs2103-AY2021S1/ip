package duke.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
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
        Deadline testDeadline = new Deadline(taskDescription, dateTime);
        String expectedOutput = "D | 0 | " + taskDescription + " | 24 Oct 2020 4:00 PM";
        assertEquals(expectedOutput, testDeadline.toDataFileFormat());
    }

    @Test
    public void test_toDataFileFormat_done(){
        String taskDescription = "Test";
        DukeDateTime dateTime = new DukeDateTime(LocalDateTime.parse("2020-10-24T16:00"), true);
        Deadline testDeadline = new Deadline(taskDescription, dateTime);
        testDeadline.markTaskAsDone();
        String expectedOutput = "D | 1 | " + taskDescription + " | 24 Oct 2020 4:00 PM";
        assertEquals(expectedOutput, testDeadline.toDataFileFormat());
    }

    @Test
    public void test_toString_notDone(){
        String taskDescription = "Test";
        DukeDateTime dateTime = new DukeDateTime(LocalDateTime.parse("2020-10-24T16:00"), true);
        Deadline testDeadline = new Deadline(taskDescription, dateTime);
        String expectedOutput = "[D][\u2718] " + taskDescription + " (by: 24 Oct 2020 4:00 PM)";
        assertEquals(expectedOutput, testDeadline.toString());
    }

    @Test
    public void test_toString_done(){
        String taskDescription = "Test";
        DukeDateTime dateTime = new DukeDateTime(LocalDateTime.parse("2020-10-24T16:00"), true);
        Deadline testDeadline = new Deadline(taskDescription, dateTime);
        testDeadline.markTaskAsDone();
        String expectedOutput = "[D][\u2713] " + taskDescription + " (by: 24 Oct 2020 4:00 PM)";
        assertEquals(expectedOutput, testDeadline.toString());
    }
}
