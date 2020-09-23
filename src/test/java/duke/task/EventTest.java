package duke.task;

import duke.helper.DateTimeHelper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void returnStringForm_validForm_correctValue() {
        LocalDate _now = LocalDate.now();
        Event ev = new Event("hello", _now, "2020-12-12", "dealineString");
        assertEquals(ev.returnStringForm(), "[E]" + "[" + "\u2718" +"] " + "hello"
                + "( at: " + DateTimeHelper.getStringRep(_now) + " 2020-12-12)");
    }
}
