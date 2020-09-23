package duke.task;

import duke.helper.DateTimeHelper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void returnStringForm_validForm_correctValue() {
        LocalDate _now = LocalDate.now();
        Deadline dl = new Deadline("hello", _now, "2020-12-12", "dealineString");
        assertEquals(dl.returnStringForm(), "[D]" + "[" + "\u2718" +"] " + "hello"
                + "( by: " + DateTimeHelper.getStringRep(_now) + " 2020-12-12)");
    }
}
