package duke.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TodoTest {
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
        Todo testTodo = new Todo(taskDescription);
        String expectedOutput = "T | 0 | " + taskDescription;
        assertEquals(expectedOutput, testTodo.toDataFileFormat());
    }

    @Test
    public void test_toDataFileFormat_done(){
        String taskDescription = "Test";
        Todo testTodo = new Todo(taskDescription);
        testTodo.markTaskAsDone();
        String expectedOutput = "T | 1 | " + taskDescription;
        assertEquals(expectedOutput, testTodo.toDataFileFormat());
    }

    @Test
    public void test_toString_notDone(){
        String taskDescription = "Test";
        Todo testTodo = new Todo(taskDescription);
        String expectedOutput = "[T][\u2718] " + taskDescription;
        assertEquals(expectedOutput, testTodo.toString());
    }

    @Test
    public void test_toString_done(){
        String taskDescription = "Test";
        Todo testTodo = new Todo(taskDescription);
        testTodo.markTaskAsDone();
        String expectedOutput = "[T][\u2713] " + taskDescription;
        assertEquals(expectedOutput, testTodo.toString());
    }

}
