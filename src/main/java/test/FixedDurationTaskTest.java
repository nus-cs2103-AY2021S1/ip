package test;

import duke.task.FixedDurationTask;
import duke.util.DukeException;

import static org.junit.jupiter.api.Assertions.fail;

public class FixedDurationTaskTest {

    @org.junit.jupiter.api.Test
    void parseDuration_noHour_success() {
        try {
            new FixedDurationTask("test", "10m");
        } catch (DukeException e) {
            fail("Should not throw exception!");
        }
    }

    @org.junit.jupiter.api.Test
    void parseDuration_noMinute_success() {
        try {
            new FixedDurationTask("test", "2h");
        } catch (DukeException e) {
            fail("Should not throw exception!");
        }
    }

    @org.junit.jupiter.api.Test
    void parseDuration_invalidString_throwException() {
        try {
            new FixedDurationTask("test", " ");
            fail("Should throw exception!");
        } catch (DukeException e) {
            System.out.println("Task failed successfully: " + e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void parseDuration_negativeValue_throwException() {
        try {
            new FixedDurationTask("test", "-10h");
            fail("Should throw exception!");
        } catch (DukeException e) {
            System.out.println("Task failed successfully: " + e.getMessage());
        }
    }


}
