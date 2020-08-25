package main.java.duke.task;

import main.java.duke.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class DeadlineTest {
    @Test
    public void constructor_invalidInput_fail() {
        try {
            new Deadline("Assignment 1","2020-09-08 0600");
        } catch (DukeException err) {
            assertEquals(err.getMessage(), "Error: Please key in as: \n " +
                    "event [title] /by YYYY-MM-DD HH:MM");
        }
    }

    @Test
    public void toString_newlyCreated_success() {
        try {
            //test 1
            assertEquals(new Deadline("Assignment 1", "2020-09-08 06:00").toString(),
                    "[D][ ] Assignment 1 (by: Sep 08 2020 06:00)");

            //test 2
            assertEquals(new Deadline("CS2101 OP1", "2020-08-25 14:35").toString(),
                    "[D][ ] CS2101 OP1 (by: Aug 25 2020 14:35)");
        } catch (DukeException err) {
            fail();
        }
    }

    @Test
    public void toString_markDone_success() {
        try {
            Deadline test1 = new Deadline("Assignment 1", "2020-09-08 06:00");
            Deadline test2 = new Deadline("CS2101 OP1", "2020-08-25 14:35");
            Deadline test3 = new Deadline("NOC Application", "2020-08-28 23:59");
            test1.setDone();
            test2.setDone();
            test3.setDone();
            assertEquals(test1.toString(), "[D][X] Assignment 1 (by: Sep 08 2020 06:00)");
            assertEquals(test2.toString(), "[D][X] CS2101 OP1 (by: Aug 25 2020 14:35)");
            assertEquals(test3.toString(), "[D][X] NOC Application (by: Aug 28 2020 23:59)");
        } catch (DukeException err) {
            fail();
        }
    }

    @Test
    public void setDone_doubleSetDone_fail() {
        try {
            Deadline test1 = new Deadline("Assignment 1", "2020-09-08 06:00");
            test1.setDone();
            test1.setDone();
        } catch (DukeException err) {
            assertEquals(err.getMessage(), "Task is already done");
        }
    }

    @Test
    public void toSaveFormat() {
        try {
            Deadline test1 = new Deadline("Assignment 1", "2020-09-08 06:00");
            Deadline test2 = new Deadline("CS2101 OP1", "2020-08-25 14:35");
            Deadline test3 = new Deadline("NOC Application", "2020-08-28 23:59");
            test1.setDone();
            assertEquals(test1.toSaveFormat(), "D | 1 | Assignment 1 | 2020-09-08 06:00");
            assertEquals(test2.toSaveFormat(), "D | 0 | CS2101 OP1 | 2020-08-25 14:35");
            assertEquals(test3.toSaveFormat(), "D | 0 | NOC Application | 2020-08-28 23:59");
        } catch (DukeException err) {
            fail();
        }
    }

}