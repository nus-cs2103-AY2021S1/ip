import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class DateAndTimeTest {
    
    @Test
    public void parseWithCorrectInputFormat() {
        //set up
        DateAndTime dT = new DateAndTime("26/08/2020", "2359");
        String dTStr = dT.toString();
        
        assertEquals("26th of August 2020, 11.59PM", dTStr);
    }
    
    @Test
    public void passedInIncorrectInputFormat() {
        Exception exception = assertThrows(DateTimeParseException.class,
                () -> new DateAndTime("26-08-2020", "2300"));
        String expected = "Text '26-08-2020' could not be parsed at index 2";
        String actual = exception.getMessage();
        
        assertTrue(actual.contains(expected));
    }
    
}
