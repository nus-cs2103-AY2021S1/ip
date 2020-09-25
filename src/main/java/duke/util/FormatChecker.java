package duke.util;

import static duke.util.IntegerChecker.isNumber;
import static duke.util.Keyword.KEYWORD_NO;
import static duke.util.Keyword.KEYWORD_ONE_SPACE;
import static duke.util.Keyword.KEYWORD_YES;
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

/**
 * Class the deals with checking the format of the user's input.
 */
public class FormatChecker {

    /**
     * Check if the event command has a description.
     *
     * @param command String array representing the command the user keyed in.
     * @throws InvalidFormatEventException When description is missing.
     */
    public static void checkEventFormat(String ... command) throws InvalidFormatEventException {
        if (!checkLengthEqualsTwo(command)) {
            throw new InvalidFormatEventException();
        }
    }

    /**
     * Check if the deadline command has a description.
     *
     * @param command String array representing the command the user keyed in.
     * @throws InvalidFormatDeadlineException When description is missing.
     */
    public static void checkDeadlineFormat(String ... command) throws InvalidFormatDeadlineException {
        if (checkLengthEqualsOne(command)) {
            throw new InvalidFormatDeadlineException();
        }
    }

    /**
     * Check if there is description in the command.
     *
     * @param command String array representing the command the user keyed in.
     * @throws EmptyTextException
     */
    public static void checkEmptyText(String ... command) throws EmptyTextException {
        if (checkLengthEqualsOne(command)) {
            throw new EmptyTextException(command[0]);
        }
    }

    /**
     * Check if the bye command has a description.
     *
     * @param command String array representing the command the user keyed in.
     * @throws InvalidFormatByeException When there are extra words behind bye.
     */
    public static void checkByeFormat(String ... command) throws InvalidFormatByeException {
        if (!checkLengthEqualsOne(command)) {
            throw new InvalidFormatByeException();
        }
    }

    /**
     * Check if the bye command has a description.
     *
     * @param command String array representing the command the user keyed in.
     * @throws InvalidFormatDeleteException When there are missing numbers or Not a number behind delete.
     */
    public static void checkDeleteFormat(TaskList tasks, String ... command) throws InvalidFormatDeleteException {
        if (checkLengthEqualsOne(command) || !isNumber(command[1])) {
            throw new InvalidFormatDeleteException();
        }

        boolean indexLessThanOrEqualsZero = parseInt(command[1]) <= 0;
        boolean indexGreaterThanSizeOfList = parseInt(command[1]) > tasks.size();

        if (indexLessThanOrEqualsZero || indexGreaterThanSizeOfList) {
            throw new InvalidFormatDeleteException();
        }
    }

    /**
     * Check if the done command has a description.
     *
     * @param command String array representing the command the user keyed in.
     * @throws InvalidFormatDoneException When there are missing numbers or Not a number behind delete.
     */
    public static void checkDoneFormat(TaskList tasks, String ... command) throws InvalidFormatDoneException {
        if (checkLengthEqualsOne(command) || !isNumber(command[1])) {
            throw new InvalidFormatDoneException();
        }

        boolean indexLessThanOrEqualsZero = parseInt(command[1]) <= 0;
        boolean indexGreaterThanSizeOfList = parseInt(command[1]) > tasks.size();

        if (indexLessThanOrEqualsZero || indexGreaterThanSizeOfList) {
            throw new InvalidFormatDoneException();
        }
    }

    /**
     * Check if the find command has a description.
     *
     * @param command String array representing the command the user keyed in.
     * @throws InvalidFormatFindException When there are missing description or user tried to key in more than
     * one keyword.
     */
    public static void checkFindFormat(String ... command) throws InvalidFormatFindException {
        if (checkLengthEqualsOne(command)) {
            throw new InvalidFormatFindException();
        }

        String[] findDescription = command[1].split(KEYWORD_ONE_SPACE);

        if (findDescription.length > 1) {
            throw new InvalidFormatFindException();
        }
    }

    /**
     * Check if the help command has a description.
     *
     * @param command String array representing the command the user keyed in.
     * @throws InvalidFormatHelpException When there are extra command behind help.
     */
    public static void checkHelpFormat(String ... command) throws InvalidFormatHelpException {
        if (!checkLengthEqualsOne(command)) {
            throw new InvalidFormatHelpException();
        }
    }

    /**
     * Check if the list command has a description.
     *
     * @param command String array representing the command the user keyed in.
     * @throws InvalidFormatListException When there are extra command behind list.
     */
    public static void checkListFormat(String ... command) throws InvalidFormatListException {
        if (!checkLengthEqualsOne(command)) {
            throw new InvalidFormatListException();
        }
    }

    /**
     * Check if the remind command has a description.
     *
     * @param command String array representing the command the user keyed in.
     * @throws InvalidFormatReminderException When there are missing description or if user key in more than the
     * required format.
     */
    public static void checkReminderFormat(TaskList tasks, String ... command) throws InvalidFormatReminderException {
        if (checkLengthEqualsOne(command)) {
            throw new InvalidFormatReminderException();
        }

        String[] reminderDescription = command[1].split(KEYWORD_ONE_SPACE);

        if (!checkLengthEqualsTwo(reminderDescription)) {
            throw new InvalidFormatReminderException();
        }

        boolean isYesCommand = reminderDescription[1].toLowerCase().equals(KEYWORD_YES);
        boolean isNoCommand = reminderDescription[1].toLowerCase().equals(KEYWORD_NO);

        if (!isYesCommand && !isNoCommand) {
            throw new InvalidFormatReminderException();
        }

        if (!isNumber(reminderDescription[0])) {
            throw new InvalidFormatReminderException();
        }

        boolean indexLessThanOrEqualsZero = parseInt(reminderDescription[0]) <= 0;
        boolean indexGreaterThanSizeOfList = parseInt(reminderDescription[0]) > tasks.size();

        if (indexLessThanOrEqualsZero || indexGreaterThanSizeOfList) {
            throw new InvalidFormatReminderException();
        }
    }

    /**
     * Returns true if length of command is 1 else false.
     *
     * @param command String array representing the command the user keyed in.
     * @return True if length of command is 1 else false.
     */
    private static boolean checkLengthEqualsOne(String ... command) {
        return command.length == 1;
    }

    /**
     * Returns true if length of command is 2 else false.
     *
     * @param command String array representing the command the user keyed in.
     * @return True if length of command is 2 else false.
     */
    private static boolean checkLengthEqualsTwo(String ... command) {
        return command.length == 2;
    }
}
