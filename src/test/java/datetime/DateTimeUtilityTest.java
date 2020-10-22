package datetime;

import duke.datetime.DateTimeUtility;
import duke.datetime.DateTimeFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeUtilityTest {
    @Test
    public void checkDateTimeTypeTest() {
        assertEquals(DateTimeUtility.checkDateTimeType("2020-04-05"),
                     DateTimeFormat.Date);
    }
}
