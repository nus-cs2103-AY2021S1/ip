package duke;

import duke.task.Deadline;
import duke.task.Event;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void genTaskFromString_undoneDeadline_correctDeadline() {
        String input = "[D][\u2718] Submit iP (by: Aug 24 2020 2359)";
        assertEquals(new Deadline("Submit iP", "Aug 24 2020 2359", false).toString(), Storage.genTaskFromString(input).toString());
    }

    @Test
    void genTaskFromString_doneEvent_correctDeadline() {
        String input = "[E][\u2713] Work on iP (at: Aug 24 2020 0900)";
        assertEquals(new Event("Work on iP", "Aug 24 2020 0900", true).toString(), Storage.genTaskFromString(input).toString());
    }
}