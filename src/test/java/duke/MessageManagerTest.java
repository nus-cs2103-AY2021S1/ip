package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Task;

class MessageManagerTest {

    @Test
    void testGetFindAllContainingMessage() throws DukeException {
        Deadline deadline = new Deadline("test", "1990-10-10 10:10", "0");
        String expectedOutput = String.format("Here are the matching tasks in your list:\n1. %s", deadline);
        List<Task> taskList = new ArrayList<>();
        taskList.add(deadline);
        assertEquals(expectedOutput, MessageManager.getFindAllContainingMessage(taskList));
    }
}
