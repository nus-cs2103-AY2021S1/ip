package duke.test;

import duke.task.Event;
import duke.util.DukeException;

import static org.junit.jupiter.api.Assertions.fail;

class EventTest {

    @org.junit.jupiter.api.Test
    void constructor_missingTime_success() {
        try {
            new Event("unit testing", "2020-01-01");
        } catch (DukeException e) {
            fail("Should create task successfully, time is Optional.");
        }
    }
}