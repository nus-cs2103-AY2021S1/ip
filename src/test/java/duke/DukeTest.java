package duke;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void MyDateTimeWrongValue(){
        try {
            MyDateTime.parse(" 12/34/5678 1234");
        } catch (DateTimeParseException e) {
            assertEquals("Text ' 12/34/5678 1234' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 34", e.getMessage());
        }
    }

    @Test
    public void MyDateTimeWrongFormat(){
        try {
            MyDateTime.parse(" 12/wrong");
        } catch (DateTimeParseException e) {
            assertEquals("Text ' 12/wrong' could not be parsed at index 4", e.getMessage());
        }
    }


    @Test
    public void MyDateTimeLoadWrongValue(){
        try {
            MyDateTime.load("1234-34-12T12:34");
        } catch (DateTimeParseException e) {
            assertEquals("Text '1234-34-12T12:34' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 34", e.getMessage());
        }
    }

    @Test
    public void MyDateTimeLoadWrongFormat(){
        try {
            MyDateTime.load("1234-wrong");
        } catch (DateTimeParseException e) {
            assertEquals("Text '1234-wrong' could not be parsed at index 5", e.getMessage());
        }
    }
}