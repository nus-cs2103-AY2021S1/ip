package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskParserTest {

    @Test
    public void testParse_validStrings_success() {
        String toDoInput = "something";
        assertEquals(new ToDo(toDoInput).toString(),
                TaskParser.parse(TaskParser.TO_DO + " " + toDoInput).toString());
        String deadlineInput = "homework /by 20-12-2020 2359";
        assertEquals(new Deadline(deadlineInput).toString(),
                TaskParser.parse(TaskParser.DEADLINE + " " + deadlineInput).toString());
        String eventInput = "gaming /at 20-12-2020 2200-2359";
        assertEquals(new Event(eventInput).toString(),
                TaskParser.parse(TaskParser.EVENT + " " + eventInput).toString());

    }

    @Test
    public void testParse_invalidString_fail() {
        String input1 = "wrong abcde /by 20-12-2020 2359";
        String input2 = "to do something";
        try {
            TaskParser.parse(input1);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("is not a valid type of command!"));
        }
        try {
            TaskParser.parse(input2);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("is not a valid type of command!"));
        }
    }




}


