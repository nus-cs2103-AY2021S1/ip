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
        @DisplayName("Test if user have missed out the /at keyword in event")
        public void missingDescriptionForEvent() {
            String[] inputArr = getInputArray("event project 2020-12-12");
            String[] messageArr = inputArr[1].split(KEYWORD_EVENT_FORMAT, 2);
            assertThrows(InvalidFormatEventException.class, () -> checkEventFormat(messageArr));
        }

        @Test
        @DisplayName("Test if user have missed out the description in event")
        public void missingDescription() {
            String[] inputArr = getInputArray("event project /at");
            inputArr[0] = inputArr[0].toLowerCase();
            String[] messageArr = inputArr[1].split(KEYWORD_EVENT_FORMAT, 2);
            assertThrows(InvalidFormatEventException.class, () -> checkEventFormat(messageArr));
        }
    }

    @Nested
    class DeadlineFormatTest {

        @Test
        @DisplayName("Test if user have missed out the /by keyword in deadline")
        public void missingByKeyword() {
            String[] inputArr = getInputArray("deadline project 2020-12-11");
            String[] messageArr = inputArr[1].split(KEYWORD_DEADLINE_FORMAT, 2);
            assertThrows(InvalidFormatDeadlineException.class, () -> checkDeadlineFormat(messageArr));
        }

        @Test
        @DisplayName("Test if user have missed out the description in deadline")
        public void missingDescription() {
            String[] inputArr = getInputArray("deadline project /by");
            String[] messageArr = inputArr[1].split(KEYWORD_DEADLINE_FORMAT, 2);
            assertThrows(InvalidFormatDeadlineException.class, () -> checkDeadlineFormat(messageArr));
        }
    }

    @Nested
    class EmptyTextFormatTest {

        @Test
        @DisplayName("Test if user only keyed in the word event")
        public void singleWordCommandForEventTest() {
            String[] inputArr = getInputArray("event");
            assertThrows(EmptyTextException.class, () -> checkEmptyText(inputArr));
        }

        @Test
        @DisplayName("Test if user only keyed in the word deadline")
        public void singleWordCommandForDeadlineTest() {
            String[] inputArr = getInputArray("deadline");
            assertThrows(EmptyTextException.class, () -> checkEmptyText(inputArr));
        }

        @Test
        @DisplayName("Test if user only keyed in the word todo")
        public void singleWordCommandForToDo() {
            String[] inputArr = getInputArray("todo");
            assertThrows(EmptyTextException.class, () -> checkEmptyText(inputArr));
        }
    }

    @Test
    public void checkByeFormatTest() {
        String[] inputArr = getInputArray("bye 2");
        assertThrows(InvalidFormatByeException.class, () -> checkByeFormat(inputArr));
    }

    @Test
    public void checkDeleteFormatTest() {
        String[] inputArr = getInputArray("delete");
        assertThrows(InvalidFormatDeleteException.class, () -> checkDeleteFormat(inputArr));
    }

    @Test
    public void checkDoneFormatTest() {
        String[] inputArr = getInputArray("done");
        assertThrows(InvalidFormatDoneException.class, () -> checkDoneFormat(inputArr));
    }

    @Nested
    class FindFormatTest {

        @Test
        @DisplayName("Test if user only keyed in find")
        public void singleWordForFind() {
            String[] inputArr = getInputArray("find");
            assertThrows(InvalidFormatFindException.class, () -> checkFindFormat(inputArr));
        }

        @Test
        @DisplayName("Test if user keyed in multiple keywords to search")
        public void multipleWordsForFind() {
            String[] inputArr = getInputArray("find me too");
            assertThrows(InvalidFormatFindException.class, () -> checkFindFormat(inputArr));
        }
    }

    @Test
    public void checkHelpFormatTest() {
        String[] inputArr = getInputArray("help 2");
        assertThrows(InvalidFormatHelpException.class, () -> checkHelpFormat(inputArr));
    }

    @Test
    public void checkListFormatTest() {
        String[] inputArr = getInputArray("list 2");
        assertThrows(InvalidFormatListException.class, () -> checkListFormat(inputArr));
    }

    @Nested
    class ReminderFormatTest {

        @Test
        @DisplayName("Test if user only keyed in remind")
        public void singleWordForFind() {
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
        @DisplayName("Test if user input an index less than zero")
        public void invalidIndexLessThanZero() {
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
        @DisplayName("Test if user input an index equals to zero")
        public void invalidIndexEqualsZero() {
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
        @DisplayName("Test if user did not key in the index of the task")
        public void missingIndex() {
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
        @DisplayName("Test if user did not key in the yes or no to activate reminder of the task")
        public void missingInstructionForReminderStatus() {
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
        @DisplayName("Test if user key in other letters beside y or n")
        public void invalidCharacterInputted() {
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
        @DisplayName("Test if user key in not a number into the index that should contain a number")
        public void invalidInputAtNumberIndex() {
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
        @DisplayName("Test if user key in number into the index that should y or n")
        public void invalidInputAtYesNoIndex() {
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
    }
}
