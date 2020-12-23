import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class ParserTest {

    private Parser parser = new Parser();

    @Test
    void parse_deleteCommandInvalidNum_throwsInvalidCommandException() {
        String invalidCommand = "delete 3";

        try {
            parser.parse(invalidCommand, 2);
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Number is invalid!", e.toString());
        }
    }

    @Test
    void parse_deleteCommandString_throwsInvalidCommandException() {
        String invalidCommand = "delete string";

        try {
            parser.parse(invalidCommand, 2);
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Please input number after the command!", e.toString());
        }
    }

    @Test
    void parse_deleteCommandNoNum_throwsInvalidCommandException() {
        String invalidCommand = "delete";

        try {
            parser.parse(invalidCommand, 2);
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Please give an index.", e.toString());
        }
    }

    @Test
    void parse_deleteCommandWrongFormat_throwsInvalidCommandException() {
        String invalidCommand = "delete 3 3";

        try {
            parser.parse(invalidCommand, 2);
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Sorry wrong format!", e.toString());
        }
    }

    @Test
    void parse_findCommandNoKeyword_throwsInvalidCommandException() {
        String invalidCommand = "find";

        try {
            parser.parse(invalidCommand, 2);
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Find what?", e.toString());
        }
    }

    @Test
    void parse_findCommandBlankKeyword_throwsInvalidCommandException() {
        String invalidCommand = "find     ";

        try {
            parser.parse(invalidCommand, 2);
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Find what?", e.toString());
        }
    }

    @Test
    void parse_findCommand_noError() {
        String validCommand = "find test this";

        try {
            assertEquals(4, parser.parse(validCommand, 2));
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    void parse_listCommand_noError() {
        String validCommand = "  list ";

        try {
            assertEquals(1, parser.parse(validCommand, 2));
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    void parse_doneCommand_noError() {
        String validCommand = "done 1";

        try {
            assertEquals(2, parser.parse(validCommand, 2));
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    void parse_deleteCommand_noError() {
        String validCommand = "delete 1";

        try {
            assertEquals(3, parser.parse(validCommand, 2));
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    void parse_updateCommand_noError() {
        String validCommand = "update time 1";

        try {
            assertEquals(5, parser.parse(validCommand, 2));
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    void parse_randomWords_noError() {
        String validCommand = "testing";

        try {
            assertEquals(6, parser.parse(validCommand, 2));
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    void getKeyword_validFindCommand_noError() {
        String findCommand = "find      test";

        assertEquals("test", parser.getKeyword(findCommand)[0]);
    }

    @Test
    void getKeyword_findCommandManyKey_noError() {
        String findCommand = "find   test   play";

        assertEquals("test", parser.getKeyword(findCommand)[0]);
        assertEquals("play", parser.getKeyword(findCommand)[1]);
    }

    @Test
    void getUpdateType_timeUpdate() {
        String updateCommand = "update time 1";

        assertEquals("time", parser.getUpdateType(updateCommand));
    }

    @Test
    void getUpdateType_descUpdate() {
        String updateCommand = "update   desc   1";

        assertEquals("desc", parser.getUpdateType(updateCommand));
    }

    @Test
    void getUpdateType_taskUpdate() {
        String updateCommand = "update task     1";

        assertEquals("task", parser.getUpdateType(updateCommand));
    }

    @Test
    void getUpdateType_dateUpdate() {
        String updateCommand = "update    date 1";

        assertEquals("date", parser.getUpdateType(updateCommand));
    }

    @Test
    void verifyTaskCanUpdate_updateTimeOnTodo_throwsInvalidCommandException() {
        String taskType = "Task";
        String updateType = "time";

        try {
            parser.verifyTaskCanUpdate(updateType, taskType);
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Sorry, can't update the task this way.", e.toString());
        }
    }

    @Test
    void verifyTaskCanUpdate_updateDateOnTodo_throwsInvalidCommandException() {
        String taskType = "Task";
        String updateType = "date";

        try {
            parser.verifyTaskCanUpdate(updateType, taskType);
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Sorry, can't update the task this way.", e.toString());
        }
    }

    @Test
    void verifyTaskCanUpdate_updateTaskOnTodo_throwsInvalidCommandException() {
        String taskType = "Task";
        String updateType = "task";

        try {
            parser.verifyTaskCanUpdate(updateType, taskType);
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    void verifyTaskCanUpdate_updateDescOnTodo_throwsInvalidCommandException() {
        String taskType = "Task";
        String updateType = "desc";

        try {
            parser.verifyTaskCanUpdate(updateType, taskType);
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    void getTask_invalidInput_throwsInvalidInputException() {
        try {
            parser.getTask("test");
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Sorry, I don't know what \"test\" means.", e.toString());
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    void getTask_missingKeyWord_throwsInvalidCommandException() {
        try {
            parser.getTask("event test ");
            fail();
        } catch (InvalidInputException e) {
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Missing /at keyword!", e.toString());
        }
    }

    @Test
    void getTask_missingDescriptionEvent_throwsInvalidCommandException() {
        try {
            parser.getTask("event /at 2020-09-09 23:59");
            fail();
        } catch (InvalidInputException e) {
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Missing task description!", e.toString());
        }
    }

    @Test
    void getTask_missingDescriptionTodo_throwsInvalidCommandException() {
        try {
            parser.getTask("todo   ");
            fail();
        } catch (InvalidInputException e) {
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Missing task description!", e.toString());
        }
    }

    @Test
    void getTask_wrongDateFormat_throwsInvalidCommandException() {
        try {
            parser.getTask("event test /at snjdan 23:59");
            fail();
        } catch (InvalidInputException e) {
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Wrong date format!", e.toString());
        }
    }

    @Test
    void getTask_wrongTimeFormat_throwsInvalidCommandException() {
        try {
            parser.getTask("event test /at 2020-09-09 23.10");
            fail();
        } catch (InvalidInputException e) {
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Wrong time format!", e.toString());
        }
    }

    @Test
    void getTask_missingDateTime_throwsInvalidCommandException() {
        try {
            parser.getTask("event test /at");
            fail();
        } catch (InvalidInputException e) {
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Missing date!", e.toString());
        }
    }
}
