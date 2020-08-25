package main.java.duke.task;

import main.java.duke.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ToDoTest {
    @Test
    public void toString_newlyCreated_success() {
        assertEquals(new ToDo("Assignment 1").toString(), "[T][ ] Assignment 1");
        assertEquals(new ToDo("CS2101 OP1").toString(), "[T][ ] CS2101 OP1");
        assertEquals(new ToDo("Get a CCA").toString(), "[T][ ] Get a CCA");
    }

    @Test
    public void toString_markDone_success() {
        ToDo test1 = new ToDo("Assignment 1");
        ToDo test2 = new ToDo("CS2101 OP1");
        ToDo test3 = new ToDo("Get a CCA");
        try {
            test1.setDone();
            test2.setDone();
            test3.setDone();
            assertEquals(test1.toString(), "[T][X] Assignment 1");
            assertEquals(test2.toString(), "[T][X] CS2101 OP1");
            assertEquals(test3.toString(), "[T][X] Get a CCA");
        } catch (DukeException err) {
            fail();
        }
    }

    @Test
    public void setDone_doubleSetDone_fail() {
        ToDo test1 = new ToDo("Assignment 1");
        try {
            test1.setDone();
            test1.setDone();
        } catch (DukeException err) {
            assertEquals(err.getMessage(), "Task is already done");
        }
    }

    @Test
    public void toSaveFormat() {
        try {
            ToDo test1 = new ToDo("Assignment 1");
            ToDo test2 = new ToDo("CS2101 OP1");
            ToDo test3 = new ToDo("Get a CCA");
            test1.setDone();
            assertEquals(test1.toSaveFormat(), "T | 1 | Assignment 1");
            assertEquals(test2.toSaveFormat(), "T | 0 | CS2101 OP1");
            assertEquals(test3.toSaveFormat(), "T | 0 | Get a CCA");
        } catch (DukeException err) {
            fail();
        }
    }
}