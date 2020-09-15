package duke.dateformats;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exceptions.DateFormatException;


public class StandardDateFormatTest {
    @Test
    public void checkTest_correctFormatInput_trueResponse() {
        StandardDateFormat sdf = new StandardDateFormat();
        Assertions.assertEquals(true, sdf.check("2020-09-20"));
    }
    @Test
    public void checkTest_incorrectFormatInput_falseResponse() {
        StandardDateFormat sdf = new StandardDateFormat();
        Assertions.assertEquals(false, sdf.check("Mon"));
    }
    @Test
    public void mapToLocalDateTest_correctFormat() throws DateFormatException {
        StandardDateFormat sdf = new StandardDateFormat();
        Assertions.assertEquals(LocalDateTime.of(2020, 9, 25, 0, 0),
                sdf.mapToLocalDateTime("2020-09-25"));
    }

}
