package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void testToString() {

        try {
            assertEquals("[D][✘] do (by: Sep 10 2012 07:00 PM)",
                    new Deadline("do",
                            new SimpleDateFormat("dd/MM/yyyy HHmm").parse("10/09/2012 1900")).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void toFileString() {
        try {
            assertEquals("D\n"
                            + "F\n"
                            + "do\n"
                            + "Sep 10 2012 07:00 PM\n",
                    new Deadline("do",
                            new SimpleDateFormat("dd/MM/yyyy HHmm").parse("10/09/2012 1900"))
                            .toFileString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
