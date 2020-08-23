package com.duke.parser;
import com.duke.exceptions.DukeException;
import com.duke.tasks.Events;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void isDoneTest_inputLetters_returnFalse() {
        boolean expectedOutput = false;
        String input = "done abc";
        boolean actualOutput = Parser.isDone(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void isDoneTest_inputThreeWords_returnFalse() {
        boolean expectedOutput = false;
        String input = "done 1 abc";
        boolean actualOutput = Parser.isDone(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void isDoneTest_normalInput_returnTrue() {
        boolean expectedOutput = true;
        String input = "done 1";
        boolean actualOutput = Parser.isDone(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void isDeleteTest_inputLetters_returnFalse() {
        boolean expectedOutput = false;
        String input = "delete abc";
        boolean actualOutput = Parser.isDelete(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void isDeleteTest_inputThreeWords_returnFalse() {
        boolean expectedOutput = false;
        String input = "delete 1 abc";
        boolean actualOutput = Parser.isDelete(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void isDeleteTest_normalInput_returnTrue() {
        boolean expectedOutput = true;
        String input = "delete 1";
        boolean actualOutput = Parser.isDelete(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void correctInputFormatTest_incorrectToDoInput_returnFalse() {
        String input = "todoeatfood";
        boolean actualOutput = Parser.correctInputFormat(input);
        boolean expectedOutput = false;
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void correctInputFormatTest_correctToDoInput_returnTrue() {
        String input = "todo eat food";
        boolean actualOutput = Parser.correctInputFormat(input);
        boolean expectedOutput = true;
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void correctInputFormatTest_incorrectDeadlineInput_returnFalse() {
        String input = "deadlines finishwritingJUnittests/by2/12/20191800";
        boolean actualOutput = Parser.correctInputFormat(input);
        boolean expectedOutput = false;
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void correctInputFormatTest_correctDeadlineInput_returnTrue() {
        String input = "deadline finish writing JUnit tests /by 2/12/2019 1800";
        boolean actualOutput = Parser.correctInputFormat(input);
        boolean expectedOutput = true;
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void correctInputFormatTest_incorrectEventsInput_returnFalse() {
        String input = "eventcs2103Tfinals/at2/12/20191800";
        boolean actualOutput = Parser.correctInputFormat(input);
        boolean expectedOutput = false;
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void correctInputFormatTest_correctEventsInput_returnTrue() {
        String input = "event CS2103T finals /at 2/12/2019 1800";
        boolean actualOutput = Parser.correctInputFormat(input);
        boolean expectedOutput = true;
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void parseDateTest_correctDateInput() {
        try {
            String input = "at 2/12/2019 1800";
            String actualOutput = Parser.parseDate(input);
            String expectedOutput = "2019-12-02 1800";
            assertEquals(expectedOutput, actualOutput);
        } catch (DukeException e) {
            Assert.fail("Exception " + e);
        }
    }

    @Test
    public void parseDateTest_incorrectDateInput() {
        DukeException e = assertThrows(DukeException.class, () -> {
           Parser.parseDate("at 2/12/20191800");
        });

        String expectedMessage = "Sorry! Format of date is wrong. " +
                "Example input should be deadline return book /by 2/12/2019 1800.";
        String actualMessage = e.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
