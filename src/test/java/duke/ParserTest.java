package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.todo.Task;

public class ParserTest {
    @Test
    public void parseTest1() {
        String input = "todo borrow book";
        try {
            Task newTask = Parser.parseTask(input);
            String description = newTask.getDescription();
            String expected = "borrow book";
            assertEquals(expected, description);
        } catch (DukeException e) {
            // will not be used
        }
    }

    @Test
    public void parseTest2() {
        String input = "deadline borrow book /at 2020-20-20";
        try {
            Task newTask = Parser.parseTask(input);
        } catch (DukeException e) {
            String output = e.getMessage();
            String expected = "The format is wrong. Please use \"by/\"!";
            assertEquals(expected, output);
        }
    }

    @Test
    public void parseTest3() {
        String input = "deadline borrow book";
        try {
            Task newTask = Parser.parseTask(input);
        } catch (DukeException e) {
            String output = e.getMessage();
            String expected = "Please provide a time for a deadline :D";
            assertEquals(expected, output);
        }
    }

    @Test
    public void parseTaskFromStorageTest() {
        String input = "D | 0 | meet friend | Sep 24 2020";
        Task newTask = Parser.parseTaskFromStorage(input);
        String description = newTask.getDescription();
        String expected = "meet friend";
        assertEquals(expected, description);
    }

    @Test
    public void standardizeTimeStringTest() {
        String input = "2000/ 12/ 20";
        String output = Parser.standardizeTimeString(input);
        String expected = "2000-12-20";
        assertEquals(expected, output);
    }
}
