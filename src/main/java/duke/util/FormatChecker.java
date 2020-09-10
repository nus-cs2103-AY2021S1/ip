package duke.util;

import static duke.util.IntegerChecker.isNumber;
import static duke.util.Keyword.KEYWORD_ONE_SPACE;
import static java.lang.Integer.parseInt;

import duke.exception.EmptyTextException;
import duke.exception.InvalidFormatByeException;
import duke.exception.InvalidFormatDeadlineException;
import duke.exception.InvalidFormatDeleteException;
import duke.exception.InvalidFormatDoneException;
import duke.exception.InvalidFormatEventException;
import duke.exception.InvalidFormatFindException;
import duke.exception.InvalidFormatHelpException;
import duke.exception.InvalidFormatListException;
import duke.exception.InvalidFormatReminderException;
import duke.tasklist.TaskList;

public class FormatChecker {
    /**
     * Checks if the event command has the description.
     *
     * @param dateTime String array representing the command the user keyed in.
     * @throws InvalidFormatEventException When description is missing.
     */
    public static void checkEventFormat(String ... dateTime) throws InvalidFormatEventException {
        if (dateTime.length == 1) {
            throw new InvalidFormatEventException();
        }
    }

    /**
     * Checks if the deadline command has the description.
     *
     * @param dateTime String array representing the command the user keyed in.
     * @throws InvalidFormatDeadlineException When description is missing.
     */
    public static void checkDeadlineFormat(String ... dateTime) throws InvalidFormatDeadlineException {
        if (dateTime.length == 1) {
            throw new InvalidFormatDeadlineException();
        }
    }

    /**
     * Checks if there is description.
     *
     * @param inputArr String array representing the command the user keyed in.
     * @throws EmptyTextException
     */
    public static void checkEmptyText(String ... inputArr) throws EmptyTextException {
        if (inputArr.length == 1) {
            throw new EmptyTextException(inputArr[0]);
        }
    }

    /**
     * Checks if the bye command has the description.
     *
     * @param inputArr String array representing the command the user keyed in.
     * @throws InvalidFormatByeException When there are extra words behind bye.
     */
    public static void checkByeFormat(String ... inputArr) throws InvalidFormatByeException {
        if (inputArr.length != 1) {
            throw new InvalidFormatByeException();
        }
    }

    /**
     * Checks if the bye command has the description.
     *
     * @param inputArr String array representing the command the user keyed in.
     * @throws InvalidFormatDeleteException When there are missing numbers or Not a number behind delete.
     */
    public static void checkDeleteFormat(String ... inputArr) throws InvalidFormatDeleteException {
        if (inputArr.length == 1 || !isNumber(inputArr[1])) {
            throw new InvalidFormatDeleteException();
        }
    }

    /**
     * Checks if the done command has the description.
     *
     * @param inputArr String array representing the command the user keyed in.
     * @throws InvalidFormatDoneException When there are missing numbers or Not a number behind delete.
     */
    public static void checkDoneFormat(String ... inputArr) throws InvalidFormatDoneException {
        if (inputArr.length == 1 || !isNumber(inputArr[1])) {
            throw new InvalidFormatDoneException();
        }
    }

    /**
     * Checks if the find command has the description.
     *
     * @param inputArr String array representing the command the user keyed in.
     * @throws InvalidFormatFindException When there are missing description or user tried to key in more than
     * one keyword.
     */
    public static void checkFindFormat(String ... inputArr) throws InvalidFormatFindException {
        if (inputArr.length == 1) {
            throw new InvalidFormatFindException();
        }
        String[] inputArr2 = inputArr[1].split(KEYWORD_ONE_SPACE);
        if (inputArr2.length > 1) {
            throw new InvalidFormatFindException();
        }
    }

    /**
     * Checks if the help command has the description.
     *
     * @param inputArr String array representing the command the user keyed in.
     * @throws InvalidFormatHelpException When there are extra command behind help.
     */
    public static void checkHelpFormat(String ... inputArr) throws InvalidFormatHelpException {
        if (inputArr.length != 1) {
            throw new InvalidFormatHelpException();
        }
    }

    /**
     * Checks if the list command has the description.
     *
     * @param inputArr String array representing the command the user keyed in.
     * @throws InvalidFormatListException When there are extra command behind list.
     */
    public static void checkListFormat(String ... inputArr) throws InvalidFormatListException {
        if (inputArr.length != 1) {
            throw new InvalidFormatListException();
        }
    }

    /**
     * Checks if the remind command has the description.
     *
     * @param inputArr String array representing the command the user keyed in.
     * @throws InvalidFormatReminderException When there are missing description or if user key in more than the
     * required format.
     */
    public static void checkReminderFormat(TaskList tasks, String ... inputArr) throws InvalidFormatReminderException {
        if (inputArr.length == 1) {
            throw new InvalidFormatReminderException();
        }
        String[] inputArr3 = inputArr[1].split(KEYWORD_ONE_SPACE);
        if (inputArr3.length != 2) {
            throw new InvalidFormatReminderException();
        }
        if (!inputArr3[1].toLowerCase().equals("y") && !inputArr3[1].toLowerCase().equals("n")) {
            throw new InvalidFormatReminderException();
        }
        if (!isNumber(inputArr3[0])) {
            throw new InvalidFormatReminderException();
        }
        if (parseInt(inputArr3[0]) <= 0 || parseInt(inputArr3[0]) > tasks.size()) {
            throw new InvalidFormatReminderException();
        }
    }
}
