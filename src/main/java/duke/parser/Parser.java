package duke.parser;

import static java.lang.Integer.parseInt;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.UnknownCommand;
import duke.exception.EmptyTextException;
import duke.exception.InvalidFormatByeException;
import duke.exception.InvalidFormatDateException;
import duke.exception.InvalidFormatDeleteException;
import duke.exception.InvalidFormatDoneException;
import duke.exception.InvalidFormatFindException;
import duke.exception.InvalidFormatListException;

/**
 * Class that simulates reading the user's input and making sense of it.
 */

public class Parser {
    private static final String KEYWORD_BYE = "bye";
    private static final String KEYWORD_LIST = "list";
    private static final String KEYWORD_DONE = "done";
    public static final String KEYWORD_TODO = "todo";
    public static final String KEYWORD_EVENT = "event";
    public static final String KEYWORD_DEADLINE = "deadline";
    private static final String KEYWORD_DELETE = "delete";
    private static final String KEYWORD_FIND = "find";

    /**
     * Checking if the user's string input is a number.
     *
     * @ param str the inputted user's index for tasks.
     * @  return boolean value of true if user's string input is a number, false otherwise.
     */
    private static boolean isNumber(String str) {
        try {
            int num = parseInt(str);
            return true;
        } catch(NumberFormatException
                e) {
            return false;
        }
    }

    /**
     *Formats the user's input timing into a LocalDateTime format.
     *
     * @ param dateAndTime The inputted user's timing.
     * @return A LocalDateTime object that contains the information of the timing.
     * @ throws InvalidFormatDateException If the inputted user's timing is not of the correct format:
     * YYYY-MM-DD HHMM or YYYY-MM-DD, an exception will be thrown to notify the user.
     */
    public static LocalDateTime formatDateTime(String dateAndTime) throws InvalidFormatDateException {
        assert dateAndTime != null;
        String[] dateFormat = dateAndTime.split(" ", 2);
        String[] date = dateFormat[0].split("-");
        String time;
        if (dateFormat.length == 1) {
            // case where he nvr input in the time
            time = "2359";
            if (date.length != 3) {
                throw new InvalidFormatDateException();
            }
        } else {
            time = dateFormat[1];
            // case where he inputs in the time
            if (date.length != 3 || time.length() != 4) {
                throw new InvalidFormatDateException();
            }
        }
        assert date != null;
        assert time != null;
        try {
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(2));
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch(DateTimeException | NumberFormatException e) {
            throw new InvalidFormatDateException();
        }
    }

    /**
     * Making sense of the user's input and outputting the relevant commands to the input.
     *
     * @ param message The inputted user's command.
     * @return A command object that executes the user's command.
     * @ throws InvalidFormatByeException Throws an InvalidFormatByeException when the format of Bye is incorrect.
     * @ throws InvalidFormatListException Throws an InvalidFormatListException when the format of List is incorrect.
     * @ throws InvalidFormatDoneException Throws an InvalidFormatDoneException when the format of Done is incorrect.
     * @ throws EmptyTextException Throws an EmptyTextException when the user did not specific anything after the command.
     * @ throws InvalidFormatDeleteException Throws an InvalidFormatDeleteException when the format of Delete is
     *incorrect.
     */
    public static Command parse(String message) throws InvalidFormatByeException, InvalidFormatListException, 
            InvalidFormatDoneException, EmptyTextException, InvalidFormatDeleteException, InvalidFormatFindException {
        assert message != null;
        String[] inputArr = message.trim().replaceAll("  +", " ").split(" ", 2);
        inputArr[0] = inputArr[0].toLowerCase();
        switch(inputArr[0]) {
        case KEYWORD_BYE:
            if (inputArr.length != 1) {
                throw new InvalidFormatByeException();
            }
            return new
                    ByeCommand(inputArr);
        case KEYWORD_LIST:
            if (inputArr.length != 1) {
                throw new InvalidFormatListException();
            }
            return new
                    ListCommand(inputArr);
        case KEYWORD_DONE:
            // checking if the input is valid
            if (inputArr.length == 1 || !isNumber(inputArr[1])) {
            throw new InvalidFormatDoneException();
            }
            return new DoneCommand(inputArr);
        case KEYWORD_EVENT:
        case KEYWORD_DEADLINE:
        case KEYWORD_TODO:
            // checking if the input is valid
            if (inputArr.length == 1) {
                throw new EmptyTextException(inputArr[0]);
            }
            return new AddCommand(inputArr);
        case KEYWORD_DELETE:
            // checking if the input is valid
            if (inputArr.length == 1 || !isNumber(inputArr[1])) {
                throw new InvalidFormatDeleteException();
            }
            return new DeleteCommand(inputArr);
        case KEYWORD_FIND:
            if (inputArr.length == 1) {
                throw new InvalidFormatFindException();
            }
            String[] inputArr2 = inputArr[1].split(" ");
            if (inputArr2.length > 1) {
                throw new InvalidFormatFindException();
            }
            return new FindCommand(inputArr);
        default:
            return new UnknownCommand(inputArr);
        }
    }
}
