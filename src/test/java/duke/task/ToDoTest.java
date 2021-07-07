package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.InvalidSaveException;

public class ToDoTest {


    /**
     * Tests to see if the ToDo created strings will be successful.
     */
    @Test
    public void createToDo_validStrings_success() {
        String input1 = "meeting";
        String input2 = "clown around";
        String output1 = "[T][\u2718] meeting";
        String output2 = "[T][\u2713] clown around";
        ToDo toDo2 = new ToDo(input2);
        toDo2.markDone();
        assertEquals(output1, new ToDo(input1).toString());
        assertEquals(output2, toDo2.toString());
    }

    /**
     * Tests 1. if the summary comes out in the form required and
     * 2. The same ToDo can be recreated from the summary.
     */
    @Test
    public void testSummary_standardToDos_success() throws InvalidSaveException, DukeException {
        String input1 = "meeting";
        String input2 = "attend carnival park";
        ToDo event1 = new ToDo(input1);
        event1.markDone();
        ToDo event2 = new ToDo(input2);
        String summary1 = event1.getSummary();
        String summary2 = event2.getSummary();
        String expectedSummary1 = "T|1|meeting";
        String expectedSummary2 = "T|0|attend carnival park";
        assertEquals(expectedSummary1, summary1);
        assertEquals(expectedSummary2, summary2);
        assertEquals(event1.toString(), ToDo.reconstructFromSummary(summary1).toString());
        assertEquals(event2.toString(), ToDo.reconstructFromSummary(summary2).toString());

    }
















}
