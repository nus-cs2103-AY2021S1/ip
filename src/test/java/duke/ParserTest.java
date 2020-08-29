package duke;

import duke.exception.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parse_noParameterForTask_exceptionThrown() {
        String testInput = "todo";
        try {
            Parser testUnit = new Parser();
            testUnit.parse(testInput);
        } catch (DukeException e) {
            assertEquals(e.toString(), "ERROR: Duke can't find your task details -> " + testInput);
        }
    }
    
    @Test
    public void parse_noParameterForDone_exceptionThrown() {
        String testInput = "done";
        try {
            Parser testUnit = new Parser();
            testUnit.parse(testInput);
        } catch (DukeException e) {
            assertEquals(e.toString(), "ERROR: Duke doesn't know what to mark as done -> " + testInput);
        }
    }

    @Test
    public void parse_noParameterForDelete_exceptionThrown() {
        String testInput = "delete";
        try {
            Parser testUnit = new Parser();
            testUnit.parse(testInput);
        } catch (DukeException e) {
            assertEquals(e.toString(), "ERROR: Duke doesn't know what to delete -> " + testInput);
        }
    }
    
    @Test
    public void parse_unknownCommand_exceptionThrown() {
        String testInput = "i want to delete 1";
        try {
            Parser testUnit = new Parser();
            testUnit.parse(testInput);
        } catch (DukeException e) {
            assertEquals(e.toString(), "ERROR: Duke can't recognise your command -> " + testInput);
        }
    }
    
    @Test
    public void parse_invalidDate_exceptionThrown() {
        String invalidDate = "2020-13-22";
        String testInput = "event some event /at " + invalidDate + " 23:59";
        try {
            Parser testUnit = new Parser();
            testUnit.parse(testInput);
        } catch (DukeException e) {
            assertEquals(e.toString(), "ERROR: Duke doesn't recognise the date/time -> " + invalidDate);
        }
    }

    @Test
    public void parse_invalidTime_exceptionThrown() {
        String invalidTime = "01:61";
        String testInput = "event some event /at 2020-12-12 " + invalidTime;
        try {
            Parser testUnit = new Parser();
            testUnit.parse(testInput);
        } catch (DukeException e) {
            assertEquals(e.toString(), "ERROR: Duke doesn't recognise the date/time -> " + invalidTime);
        }
    }
}
