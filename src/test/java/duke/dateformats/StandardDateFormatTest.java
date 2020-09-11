package duke.dateformats;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


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
    public void mapToLocalDateTest_correctFormat() {
        StandardDateFormat sdf = new StandardDateFormat();
        Assertions.assertEquals(LocalDate.of(2020, 9, 25), sdf.mapToLocalDate("2020-09-25"));
    }

}
