package duke.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class ParserTest {

    @Test
    public void testParseNoCommand() {
        // Test many spaces and enter is typed.
        Exception exception = assertThrows(DukeException.class, () -> Parser.parse("           \n\n       "));
        String expectedMessage = "Please type a duke command";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        // Test nothing is entered.
        Exception exception1 = assertThrows(DukeException.class, () -> Parser.parse(""));
        String expectedMessage1 = "Please type a duke command";
        String actualMessage1 = exception1.getMessage();
        assertTrue(actualMessage1.contains(expectedMessage1));

        // Test no keyword is entered.
        Exception exception2 = assertThrows(DukeException.class, () -> Parser.parse("Test no forward slash"));
        String expectedMessage2 = "Please enter a valid command";
        String actualMessage2 = exception2.getMessage();
        assertTrue(actualMessage2.contains(expectedMessage2));

        // Test no keyword and space is entered.
        Exception exception3 = assertThrows(DukeException.class, () -> Parser.parse("TestNoSpaceIsTyped"));
        String expectedMessage3 = "Please enter a valid command";
        String actualMessage3 = exception3.getMessage();
        assertTrue(actualMessage3.contains(expectedMessage3));

    }


    @Test
    public void testOneKeywordIsEntered(){


    }




}