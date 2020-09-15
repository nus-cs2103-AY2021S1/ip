package duke.dateformats;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exceptions.DateFormatException;


public class StandardTimeFormatTest {
    @Test
    public void checkTest_correctFormatWithTimeInput_trueResponse() {
        StandardTimeFormat stf = new StandardTimeFormat();
        Assertions.assertEquals(true, stf.check("2020-09-25 12:00"));
    }

    @Test
    public void mapToLocalDateTest_withTime() throws DateFormatException {
        StandardTimeFormat sdf = new StandardTimeFormat();
        Assertions.assertEquals(LocalDateTime.of(2020, 9, 25, 12, 30),
                sdf.mapToLocalDateTime("2020-09-25 12:30"));
    }

}
