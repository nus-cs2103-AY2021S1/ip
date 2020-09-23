package duke.util;

import static duke.util.DateFormatter.formatDateTime;
import static duke.util.FormatChecker.checkByeFormat;
import static duke.util.FormatChecker.checkDeadlineFormat;
import static duke.util.FormatChecker.checkDeleteFormat;
import static duke.util.FormatChecker.checkDoneFormat;
import static duke.util.FormatChecker.checkEmptyText;
import static duke.util.FormatChecker.checkEventFormat;
import static duke.util.FormatChecker.checkFindFormat;
import static duke.util.FormatChecker.checkHelpFormat;
import static duke.util.FormatChecker.checkListFormat;
import static duke.util.FormatChecker.checkReminderFormat;
import static duke.util.Keyword.KEYWORD_DEADLINE_FORMAT;
import static duke.util.Keyword.KEYWORD_EVENT_FORMAT;
import static duke.util.Keyword.KEYWORD_MULTIPLE_SPACE;
import static duke.util.Keyword.KEYWORD_ONE_SPACE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import duke.exception.EmptyTextException;
import duke.exception.InvalidFormatByeException;
import duke.exception.InvalidFormatDateException;
import duke.exception.InvalidFormatDeadlineException;
import duke.exception.InvalidFormatDeleteException;
import duke.exception.InvalidFormatDoneException;
import duke.exception.InvalidFormatEventException;
import duke.exception.InvalidFormatFindException;
import duke.exception.InvalidFormatHelpException;
import duke.exception.InvalidFormatListException;
import duke.exception.InvalidFormatReminderException;
import duke.task.Event;
import duke.tasklist.TaskList;

public class FormatCheckerTest {

    private FormatChecker formatChecker;

    private static String[] getInputArray(String message) {
        return message.trim().replaceAll(KEYWORD_MULTIPLE_SPACE, KEYWORD_ONE_SPACE).split(KEYWORD_ONE_SPACE, 2);
    }

    @BeforeEach
    void init() {
        formatChecker = new FormatChecker();
    }

    @Nested
    class EventFormatTest {

        @Test
        @DisplayName("Missing /at for event")
        public void checkEventFormat_missingAtInInput_invalidFormatEventExceptionThrown() {
            String[] inputArr = getInputArray("event project 2020-12-12");
            String[] messageArr = inputArr[1].split(KEYWORD_EVENT_FORMAT, 2);
            assertThrows(InvalidFormatEventException.class, () -> checkEventFormat(messageArr));
        }

        @Test
        @DisplayName("Missing date for event")
        public void checkEventFormat_missingDate_invalidFormatEventExceptionThrown() {
            String[] inputArr = getInputArray("event project /at");
            inputArr[0] = inputArr[0].toLowerCase();
            String[] messageArr = inputArr[1].split(KEYWORD_EVENT_FORMAT, 2);
            assertThrows(InvalidFormatEventException.class, () -> checkEventFormat(messageArr));
        }

        @Test
        @DisplayName("Missing description for event")
        public void checkEventFormat_missingDescription_invalidFormatEventExceptionThrown() {
            String[] inputArr = getInputArray("event /at 2020-12-12");
            inputArr[0] = inputArr[0].toLowerCase();
            String[] messageArr = inputArr[1].split(KEYWORD_EVENT_FORMAT, 2);
            assertThrows(InvalidFormatEventException.class, () -> checkEventFormat(messageArr));
        }

        @Test
        @DisplayName("Valid format for event")
        public void checkEventFormat_validFormat_success() {
            String[] inputArr = getInputArray("event project /at 2020-12-12");
            inputArr[0] = inputArr[0].toLowerCase();
            String[] messageArr = inputArr[1].split(KEYWORD_EVENT_FORMAT, 2);
            assertDoesNotThrow(() -> checkEventFormat(messageArr));
        }
    }

    @Nested
    class DeadlineFormatTest {

        @Test
        @DisplayName("Missing /by for deadline")
        public void checkDeadlineFormat_missingByInInput_invalidFormatDeadlineExceptionThrown() {
            String[] inputArr = getInputArray("deadline project 2020-12-11");
            String[] messageArr = inputArr[1].split(KEYWORD_DEADLINE_FORMAT, 2);
            assertThrows(InvalidFormatDeadlineException.class, () -> checkDeadlineFormat(messageArr));
        }

        @Test
        @DisplayName("Missing date for deadline")
        public void checkDeadlineFormat_missingDate_invalidFormatDeadlineExceptionThrown() {
            String[] inputArr = getInputArray("deadline project /by");
            String[] messageArr = inputArr[1].split(KEYWORD_DEADLINE_FORMAT, 2);
            assertThrows(InvalidFormatDeadlineException.class, () -> checkDeadlineFormat(messageArr));
        }

        @Test
        @DisplayName("Missing description for deadline")
        public void checkDeadlineFormat_missingDescription_invalidFormatDeadlineExceptionThrown() {
            String[] inputArr = getInputArray("deadline /by 2020-11-11");
            String[] messageArr = inputArr[1].split(KEYWORD_DEADLINE_FORMAT, 2);
            assertThrows(InvalidFormatDeadlineException.class, () -> checkDeadlineFormat(messageArr));
        }

        @Test
        @DisplayName("Valid format for deadline")
        public void checkDeadlineFormat_validFormat_success() {
            String[] inputArr = getInputArray("deadline project /by 2020-11-11");
            String[] messageArr = inputArr[1].split(KEYWORD_DEADLINE_FORMAT, 2);
            assertDoesNotThrow(() -> checkDeadlineFormat(messageArr));
        }
    }

    @Nested
    class EmptyTextFormatTest {

        @Test
        @DisplayName("Missing commands after event")
        public void checkEmptyText_missingCommandsAfterEvent_emptyTextExceptionThrown() {
            String[] inputArr = getInputArray("event");
            assertThrows(EmptyTextException.class, () -> checkEmptyText(inputArr));
        }

        @Test
        @DisplayName("Missing commands after deadline")
        public void checkEmptyText_missingCommandsAfterDeadline_emptyTextExceptionThrown() {
            String[] inputArr = getInputArray("deadline");
            assertThrows(EmptyTextException.class, () -> checkEmptyText(inputArr));
        }

        @Test
        @DisplayName("Missing commands after todo")
        public void checkEmptyText_missingCommandsAfterToDo_emptyTextExceptionThrown() {
            String[] inputArr = getInputArray("todo");
            assertThrows(EmptyTextException.class, () -> checkEmptyText(inputArr));
        }
    }

    @Nested
    class ByeFormatTest {

        @Test
        @DisplayName("Extra commands after bye (check for extra words)")
        public void checkByeFormat_extraWordAfterBye_invalidFormatByeExceptionThrown() {
            String[] inputArr = getInputArray("bye me");
            assertThrows(InvalidFormatByeException.class, () -> checkByeFormat(inputArr));
        }

        @Test
        @DisplayName("Extra commands after bye (check for extra numbers)")
        public void checkByeFormat_extraNumberAfterBye_invalidFormatByeExceptionThrown() {
            String[] inputArr = getInputArray("bye 2");
            assertThrows(InvalidFormatByeException.class, () -> checkByeFormat(inputArr));
        }

        @Test
        @DisplayName("Valid format for bye")
        public void checkByeFormat_validFormat_success() {
            String[] inputArr = getInputArray("bye");
            assertDoesNotThrow(() -> checkByeFormat(inputArr));
        }
    }

    @Nested
    class DeleteFormatTest {

        @Test
        @DisplayName("Missing index for delete")
        public void checkDeleteFormat_missingIndex_invalidFormatDeleteExceptionThrown() {
            String[] inputArr = getInputArray("delete");
            TaskList tasks = new TaskList();
            assertThrows(InvalidFormatDeleteException.class, () -> checkDeleteFormat(tasks, inputArr));
        }

        @Test
        @DisplayName("0 index being used for delete")
        public void checkDeleteFormat_zeroIndex_invalidFormatDeleteExceptionThrown() {
            String[] inputArr = getInputArray("delete 0");
            TaskList tasks = new TaskList();
            assertThrows(InvalidFormatDeleteException.class, () -> checkDeleteFormat(tasks, inputArr));
        }

        @Test
        @DisplayName("Negative index being used for delete")
        public void checkDeleteFormat_outOfBoundIndex_invalidFormatDeleteExceptionThrown() {
            String[] inputArr = getInputArray("delete -1");
            TaskList tasks = new TaskList();
            assertThrows(InvalidFormatDeleteException.class, () -> checkDeleteFormat(tasks, inputArr));
        }

        @Test
        @DisplayName("Valid format for delete")
        public void checkDeleteFormat_validInput_success() {
            try {
                String[] inputArr = getInputArray("delete 1");
                TaskList tasks = new TaskList();
                String[] dateTime = "event project /at 2020-11-11".split(KEYWORD_EVENT_FORMAT, 2);
                tasks.add(new Event(dateTime[0], formatDateTime(dateTime[1])));
                assertDoesNotThrow(() -> checkDeleteFormat(tasks, inputArr));
            } catch (InvalidFormatDateException e) {
                System.out.println(e);
            }
        }
    }

    @Nested
    class DoneFormatTest {

        @Test
        @DisplayName("Missing index for done")
        public void checkDoneFormat_missingIndex_invalidFormatDoneExceptionThrown() {
            String[] inputArr = getInputArray("done");
            TaskList tasks = new TaskList();
            assertThrows(InvalidFormatDoneException.class, () -> checkDoneFormat(tasks, inputArr));
        }

        @Test
        @DisplayName("0 index being used for done")
        public void checkDoneFormat_zeroIndex_invalidFormatDoneExceptionThrown() {
            String[] inputArr = getInputArray("done 0");
            TaskList tasks = new TaskList();
            assertThrows(InvalidFormatDoneException.class, () -> checkDoneFormat(tasks, inputArr));
        }

        @Test
        @DisplayName("Negative index being used for done")
        public void checkDoneFormat_outOfBoundIndex_invalidFormatDoneExceptionThrown() {
            String[] inputArr = getInputArray("done -1");
            TaskList tasks = new TaskList();
            assertThrows(InvalidFormatDoneException.class, () -> checkDoneFormat(tasks, inputArr));
        }

        @Test
        @DisplayName("Valid format for done")
        public void checkDoneFormat_validInput_success() {
            try {
                String[] inputArr = getInputArray("done 1");
                TaskList tasks = new TaskList();
                String[] dateTime = "event project /at 2020-11-11".split(KEYWORD_EVENT_FORMAT, 2);
                tasks.add(new Event(dateTime[0], formatDateTime(dateTime[1])));
                assertDoesNotThrow(() -> checkDoneFormat(tasks, inputArr));
            } catch (InvalidFormatDateException e) {
                System.out.println(e);
            }
        }
    }

    @Nested
    class FindFormatTest {

        @Test
        @DisplayName("Missing Keyword for find")
        public void checkFindFormat_missingKeyword_invalidFormatFindExceptionThrown() {
            String[] inputArr = getInputArray("find");
            assertThrows(InvalidFormatFindException.class, () -> checkFindFormat(inputArr));
        }

        @Test
        @DisplayName("Too many keywords for find")
        public void checkFindFormat_multipleKeywords_invalidFormatFindExceptionThrown() {
            String[] inputArr = getInputArray("find me too");
            assertThrows(InvalidFormatFindException.class, () -> checkFindFormat(inputArr));
        }

        @Test
        @DisplayName("Valid format for find")
        public void checkFindFormat_validInput_success() {
            String[] inputArr = getInputArray("find duke");
            assertDoesNotThrow(() -> checkFindFormat(inputArr));
        }

    }

    @Nested
    class HelpFormatTest {

        @Test
        @DisplayName("Extra numbers after help")
        public void checkHelpFormat_extraNumberAfterHelp_invalidFormatHelpExceptionThrown() {
            String[] inputArr = getInputArray("help 2");
            assertThrows(InvalidFormatHelpException.class, () -> checkHelpFormat(inputArr));
        }

        @Test
        @DisplayName("Extra words after help")
        public void checkHelpFormat_extraWordAfterHelp_invalidFormatHelpExceptionThrown() {
            String[] inputArr = getInputArray("help me");
            assertThrows(InvalidFormatHelpException.class, () -> checkHelpFormat(inputArr));
        }


        @Test
        @DisplayName("Valid format for help")
        public void checkHelpFormat_validInput_success() {
            String[] inputArr = getInputArray("help");
            assertDoesNotThrow(() -> checkHelpFormat(inputArr));
        }

    }

    @Nested
    class ListFormatTest {

        @Test
        @DisplayName("Extra words after list")
        public void checkListFormat_extraWordAfterList_invalidFormatListExceptionThrown() {
            String[] inputArr = getInputArray("list me");
            assertThrows(InvalidFormatListException.class, () -> checkListFormat(inputArr));
        }

        @Test
        @DisplayName("Extra numbers after list")
        public void checkListFormat_extraNumberAfterList_invalidFormatListExceptionThrown() {
            String[] inputArr = getInputArray("list 2");
            assertThrows(InvalidFormatListException.class, () -> checkListFormat(inputArr));
        }

        @Test
        @DisplayName("Valid format for list")
        public void checkListFormat_validInput_success() {
            String[] inputArr = getInputArray("list");
            assertDoesNotThrow(() -> checkListFormat(inputArr));
        }
    }

    @Nested
    class RemindFormatTest {

        @Test
        @DisplayName("Missing index and command for remind")
        public void checkReminderFormat_missingIndexAndCommand_invalidFormatReminderExceptionThrown() {
            try {
                String[] inputArr = getInputArray("remind");
                TaskList tasks = new TaskList();
                String[] dateTime = "event project /at 2020-11-11".split(KEYWORD_EVENT_FORMAT, 2);
                tasks.add(new Event(dateTime[0], formatDateTime(dateTime[1])));
                assertThrows(InvalidFormatReminderException.class, () -> checkReminderFormat(tasks, inputArr));
            } catch (InvalidFormatDateException e) {
                System.out.println(e);
            }
        }

        @Test
        @DisplayName("Negative index being used for remind")
        public void checkReminderFormat_negativeIndex_invalidFormatReminderExceptionThrown() {
            try {
                String[] inputArr = getInputArray("remind -1 y");
                TaskList tasks = new TaskList();
                String[] dateTime = "event project /at 2020-11-11".split(KEYWORD_EVENT_FORMAT, 2);
                tasks.add(new Event(dateTime[0], formatDateTime(dateTime[1])));
                assertThrows(InvalidFormatReminderException.class, () -> checkReminderFormat(tasks, inputArr));
            } catch (InvalidFormatDateException e) {
                System.out.println(e);
            }
        }

        @Test
        @DisplayName("0 index being used for remind")
        public void checkReminderFormat_zeroIndex_invalidFormatReminderExceptionThrown() {
            try {
                String[] inputArr = getInputArray("remind 0 y");
                TaskList tasks = new TaskList();
                String[] dateTime = "event project /at 2020-11-11".split(KEYWORD_EVENT_FORMAT, 2);
                tasks.add(new Event(dateTime[0], formatDateTime(dateTime[1])));
                assertThrows(InvalidFormatReminderException.class, () -> checkReminderFormat(tasks, inputArr));
            } catch (InvalidFormatDateException e) {
                System.out.println(e);
            }
        }

        @Test
        @DisplayName("Missing Index for remind")
        public void checkReminderFormat_missingIndex_invalidFormatReminderExceptionThrown() {
            try {
                String[] inputArr = getInputArray("remind y");
                TaskList tasks = new TaskList();
                String[] dateTime = "event project /at 2020-11-11".split(KEYWORD_EVENT_FORMAT, 2);
                tasks.add(new Event(dateTime[0], formatDateTime(dateTime[1])));
                assertThrows(InvalidFormatReminderException.class, () -> checkReminderFormat(tasks, inputArr));
            } catch (InvalidFormatDateException e) {
                System.out.println(e);
            }
        }

        @Test
        @DisplayName("Missing command for remind")
        public void checkReminderFormat_missingCommand_invalidFormatReminderExceptionThrown() {
            try {
                String[] inputArr = getInputArray("remind 1");
                TaskList tasks = new TaskList();
                String[] dateTime = "event project /at 2020-11-11".split(KEYWORD_EVENT_FORMAT, 2);
                tasks.add(new Event(dateTime[0], formatDateTime(dateTime[1])));
                assertThrows(InvalidFormatReminderException.class, () -> checkReminderFormat(tasks, inputArr));
            } catch (InvalidFormatDateException e) {
                System.out.println(e);
            }
        }

        @Test
        @DisplayName("Invalid command for remind")
        public void checkReminderFormat_invalidCommandForYesOrNo_invalidFormatReminderExceptionThrown() {
            try {
                String[] inputArr = getInputArray("remind 1 b");
                TaskList tasks = new TaskList();
                String[] dateTime = "event project /at 2020-11-11".split(KEYWORD_EVENT_FORMAT, 2);
                tasks.add(new Event(dateTime[0], formatDateTime(dateTime[1])));
                assertThrows(InvalidFormatReminderException.class, () -> checkReminderFormat(tasks, inputArr));
            } catch (InvalidFormatDateException e) {
                System.out.println(e);
            }
        }

        @Test
        @DisplayName("Invalid input at index for remind")
        public void checkReminderFormat_invalidInputForIndex_invalidFormatReminderExceptionThrown() {
            try {
                String[] inputArr = getInputArray("remind b y");
                TaskList tasks = new TaskList();
                String[] dateTime = "event project /at 2020-11-11".split(KEYWORD_EVENT_FORMAT, 2);
                tasks.add(new Event(dateTime[0], formatDateTime(dateTime[1])));
                assertThrows(InvalidFormatReminderException.class, () -> checkReminderFormat(tasks, inputArr));
            } catch (InvalidFormatDateException e) {
                System.out.println(e);
            }
        }

        @Test
        @DisplayName("Invalid input at command for remind")
        public void checkReminderFormat_invalidInputForYesOrNo_invalidFormatReminderExceptionThrown() {
            try {
                String[] inputArr = getInputArray("remind 1 2");
                TaskList tasks = new TaskList();
                String[] dateTime = "event project /at 2020-11-11".split(KEYWORD_EVENT_FORMAT, 2);
                tasks.add(new Event(dateTime[0], formatDateTime(dateTime[1])));
                assertThrows(InvalidFormatReminderException.class, () -> checkReminderFormat(tasks, inputArr));
            } catch (InvalidFormatDateException e) {
                System.out.println(e);
            }
        }

        @Test
        @DisplayName("Valid format for remind")
        public void checkReminderFormat_validInput_success() {
            try {
                String[] inputArr = getInputArray("remind 1 y");
                TaskList tasks = new TaskList();
                String[] dateTime = "event project /at 2020-11-11".split(KEYWORD_EVENT_FORMAT, 2);
                tasks.add(new Event(dateTime[0], formatDateTime(dateTime[1])));
                assertDoesNotThrow(() -> checkReminderFormat(tasks, inputArr));
            } catch (InvalidFormatDateException e) {
                System.out.println(e);
            }
        }
    }
}
