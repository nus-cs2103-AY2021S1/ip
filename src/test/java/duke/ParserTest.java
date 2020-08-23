package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SaveCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import duke.task.Task;

public class ParserTest {

    @Test
    public void parseInput_byeInput_correctCmd() {
        try {
            Command c = Parser.parseInput("bye");
            assertTrue(c instanceof ByeCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseInput_saveInput_correctCmd() {
        try {
            Command c = Parser.parseInput("save");
            assertTrue(c instanceof SaveCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseInput_listInput_correctCmd() {
        try {
            Command c = Parser.parseInput("list");
            assertTrue(c instanceof ListCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseInput_doneInputCorrect_correctCmd() {
        try {
            Command c = Parser.parseInput("done 1");
            assertTrue(c instanceof DoneCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseInput_doneInputWrong_throwDukeException() {
        try {
            Command c = Parser.parseInput("done abc");
            fail();
        } catch (DukeException e) {
            assertEquals(
                    "Please check your inputs again, ensure words are spaced and numbers(if any) are correct.",
                    e.getMessage());
        }
    }

    @Test
    public void parseInput_deleteInputCorrect_correctCmd() {
        try {
            Command c = Parser.parseInput("delete 1");
            assertTrue(c instanceof DeleteCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseInput_deleteInputWrong_throwDukeException() {
        try {
            Command c = Parser.parseInput("delete abc");
            fail();
        } catch (DukeException e) {
            assertEquals(
                    "Please check your inputs again, ensure words are spaced and numbers(if any) are correct.",
                    e.getMessage());
        }
    }

    @Test
    public void parseInput_findInputCorrect_correctCmd() {
        try {
            Command c = Parser.parseInput("find a");
            assertTrue(c instanceof FindCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseInput_findInputWrong_throwDukeException() {
        try {
            Command c = Parser.parseInput("find ");
            fail();
        } catch (DukeException e) {
            assertEquals(
                    "Please check your inputs again, ensure words are spaced and numbers(if any) are correct.",
                    e.getMessage());
        }
    }

    @Test
    public void parseInput_todoInput_correctCmd() {
        try {
            Command c = Parser.parseInput("todo test");
            assertTrue(c instanceof TodoCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseInput_deadlineInput_correctCmd() {
        try {
            Command c = Parser.parseInput("deadline test /by 2020-08-22");
            assertTrue(c instanceof DeadlineCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseInput_eventInput_correctCmd() {
        try {
            Command c = Parser.parseInput("event test /at 2020-08-22");
            assertTrue(c instanceof EventCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseInput_emptyInput_throwDukeException() {
        try {
            Parser.parseInput("");
            fail();
        } catch (DukeException e) {
            assertEquals("Input cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void parseInput_taskInputEmptyDescription_throwDukeException() {
        try {
            Parser.parseInput("todo");
            fail();
        } catch (DukeException e) {
            assertEquals("todo description cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void parseInput_deadlineInputNoBreakPt_throwDukeException() {
        try {
            Parser.parseInput("deadline test 2020-08-22");
            fail();
        } catch (DukeException e) {
            assertEquals("/by keyword must be in input for all deadline commands!", e.getMessage());
        }
    }

    @Test
    public void parseInput_eventInputNoBreakPt_throwDukeException() {
        try {
            Parser.parseInput("event test 2020-08-22");
            fail();
        } catch (DukeException e) {
            assertEquals("/at keyword must be in input for all event commands!", e.getMessage());
        }
    }

    @Test
    public void parseInput_inputWithIllegalChar_throwDukeException() {
        try {
            Parser.parseInput("event test| 2020-08-22");
            fail();
        } catch (DukeException e) {
            assertEquals("Illegal Character: |", e.getMessage());
        }
    }

    @Test
    public void parseTaskData_todoCorrect_correctOutput() {
        try {
            Task t = Parser.parseTaskData("T|1|test");
            assertEquals("[T][\u2713] test", t.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseTaskData_deadlineCorrect_correctOutput() {
        try {
            Task t = Parser.parseTaskData("D|0|test|2020-08-22");
            assertEquals("[D][\u2718] test (by: Aug 22 2020)", t.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseTaskData_deadlineWrong_throwDukeException() {
        try {
            Task t = Parser.parseTaskData("D|0|test|2020-8-22");
            fail();
        } catch (DukeException e) {
            assertEquals("supplied data: 2020-8-22 does not conform to yyyy-mm-dd", e.getMessage());
        }
    }

    @Test
    public void parseTaskData_eventCorrect_correctOutput() {
        try {
            Task t = Parser.parseTaskData("E|0|test|2020-08-22");
            assertEquals("[E][\u2718] test (at: Aug 22 2020)", t.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseTaskData_eventWrong_throwDukeException() {
        try {
            Task t = Parser.parseTaskData("E|0|test|2020-8-22");
            fail();
        } catch (DukeException e) {
            assertEquals("supplied data: 2020-8-22 does not conform to yyyy-mm-dd", e.getMessage());
        }
    }
}
