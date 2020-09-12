package duke.dateformats;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class DayOnlyFormatTest {
    @Test
    public void checkTest_correctInput_trueResponse() {
        DayOnlyFormat format = new DayOnlyFormat();
        Assertions.assertEquals(true, format.check("Tue"));
    }

    @Test
    public void checkTest_incorrectInput_falseResponse() {
        DayOnlyFormat format = new DayOnlyFormat();
        Assertions.assertEquals(false, format.check("2020-09-25"));
    }
    @Test
    public void mapToLocalDateTest_validFormat() {
        DayOnlyFormat format = new DayOnlyFormat();
        Assertions.assertEquals(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY)),
                format.mapToLocalDate("Wed"));
    }
}
