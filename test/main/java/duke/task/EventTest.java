package main.java.duke.task;

import main.java.duke.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class EventTest {
    @Test
    public void constructor_invalidInput_fail() {
        try {
            new Event("Assignment 1","2020-09-08 0600 08:00");
        } catch (DukeException err) {
            assertEquals(err.getMessage(), "Error: Please key in as: \n" +
                    "event [title] /at YYYY-MM-DD [startTime] [endTime] where start and end time is in HH:MM ");
        }
    }

    @Test
    public void toString_newlyCreated_success() {
        try {
            //test 1
            assertEquals(new Event("Assignment 1", "2020-09-08 06:00 08:00").toString(),
                    "[E][ ] Assignment 1 (at: Sep 08 2020 06:00 - 08:00)");

            //test 2
            assertEquals(new Event("CS2101 OP1", "2020-08-25 14:35 16:35").toString(),
                    "[E][ ] CS2101 OP1 (at: Aug 25 2020 14:35 - 16:35)");
        } catch (DukeException err) {
            fail();
        }
    }

    @Test
    public void toString_markDone_success() {
        try {
            Event test1 = new Event("Assignment 1", "2020-09-08 06:00 08:00");
            Event test2 = new Event("CS2101 OP1", "2020-08-25 14:35 16:35");
            Event test3 = new Event("NOC Briefing", "2020-08-28 16:00 17:00");
            test1.setDone();
            test2.setDone();
            test3.setDone();
            assertEquals(test1.toString(), "[E][X] Assignment 1 (at: Sep 08 2020 06:00 - 08:00)");
            assertEquals(test2.toString(), "[E][X] CS2101 OP1 (at: Aug 25 2020 14:35 - 16:35)");
            assertEquals(test3.toString(), "[E][X] NOC Briefing (at: Aug 28 2020 16:00 - 17:00)");
        } catch (DukeException err) {
            fail();
        }
    }

    @Test
    public void setDone_doubleSetDone_fail() {
        try {
            Event test1 = new Event("Assignment 1", "2020-09-08 06:00 08:00");
            test1.setDone();
            test1.setDone();
        } catch (DukeException err) {
            assertEquals(err.getMessage(), "Task is already done");
        }
    }

    @Test
    public void toSaveFormat() {
        try {
            Event test1 = new Event("Assignment 1", "2020-09-08 06:00 08:00");
            Event test2 = new Event("CS2101 OP1", "2020-08-25 14:35 16:35");
            Event test3 = new Event("NOC Briefing", "2020-08-28 16:00 17:00");
            test1.setDone();
            assertEquals(test1.toSaveFormat(), "E | 1 | Assignment 1 | 2020-09-08 06:00 08:00");
            assertEquals(test2.toSaveFormat(), "E | 0 | CS2101 OP1 | 2020-08-25 14:35 16:35");
            assertEquals(test3.toSaveFormat(), "E | 0 | NOC Briefing | 2020-08-28 16:00 17:00");
        } catch (DukeException err) {
            fail();
        }
    }

}