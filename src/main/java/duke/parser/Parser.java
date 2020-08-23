package duke.parser;

import duke.commands.*;
import duke.exception.*;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import static java.lang.Integer.parseInt;

/**
 * Class that simulates reading the user's input and making sense of it
 */

public class Parser {
    private static final String KEYWORD_BYE = "bye";
    private static final String KEYWORD_LIST = "list";
    private static final String KEYWORD_DONE = "done";
    private static final String KEYWORD_TODO = "todo";
    private static final String KEYWORD_EVENT = "event";
    private static final String KEYWORD_DEADLINE = "deadline";
    private static final String KEYWORD_DELETE = "delete";
    private static final String KEYWORD_FIND = "find";

    /**
     * Checking if the user's string input is a number
     * 
     * @param str the inputted user's index for tasks
     * @return boolean value of true if user's string input is a number, false otherwise.
     */
    private static boolean isNumber(String str) {
        try {
            int num = parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checking if the type of command is of type todo
     * 
     * @param type The inputted user's type
     * @return Boolean value of true if user's inputted type is todo, false otherwise.
     */
    public static boolean isTODO(String type) {
        return type.equals(KEYWORD_TODO);
    }

    /**
     * Checking if the type of command is of type deadline
     * 
     * @param type The inputted user's type
     * @return Boolean value of true if user's inputted type is deadline, false otherwise.
     */
    public static boolean isDeadline(String type) {
        return type.equals(KEYWORD_DEADLINE);
    }
    
    /**
     * Checking if the type of command is of type event
     * 
     * @param type The inputted user's type
     * @return Boolean value of true if user's inputted type is event, false otherwise.
     */
    public static boolean isEvent(String type) {
        return type.equals(KEYWORD_EVENT);
    }

    /**
     * Checking if the type of command is of type done
     * 
     * @param type The inputted user's type
     * @return boolean value of true if user's inputted type is done, false otherwise.
     */
    private static boolean isValidDone(String type) {
        return type.equals(KEYWORD_DONE);
    }

    /**
     * Checking if the type of command is of type bye
     * 
     * @param type The inputted user's type
     * @return boolean value of true if user's inputted type is bye, false otherwise.
     */
    private static boolean isEnd(String type) {
        return type.equals(KEYWORD_BYE);
    }

    /**
     * Checking if the type of command is of type list
     * 
     * @param type The inputted user's type
     * @return boolean value of true if user's inputted type is list, false otherwise.
     */
    private static boolean isList(String type) {
        return type.equals(KEYWORD_LIST);
    }

    /**
     * Checking if the type of command is of type task(deadline or todo or event)
     * 
     * @param type The inputted user's type
     * @return boolean value of true if user's inputted type is task, false otherwise.
     */
    private static boolean isTask(String type) {
        return isDeadline(type) || isTODO(type) || isEvent(type);
    }

    /**
     * Checking if the type of command is of type delete
     * 
     * @param type The inputted user's type
     * @return boolean value of true if user's inputted type is delete, false otherwise.
     */
    private static boolean isDelete(String type) {
        return type.equals(KEYWORD_DELETE);
    }
    
    private static boolean isFind(String type ){ return type.equals(KEYWORD_FIND); }
    

    /**
     * Formats the user's input timing into a LocalDateTime format
     * 
     * @param s The inputted user's timing
     * @return A LocalDateTime object that contains the information of the timing
     * @throws InvalidFormatDateException If the inputted user's timing is not of the correct format, 
     * YYYY-MM-DD HHMM or YYYY-MM-DD HMMM, an exception will be thrown to notify the user.
     */
    public static LocalDateTime formatDateTime(String s) throws InvalidFormatDateException {
        String[] dateFormat = s.split(" ",2);
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
        try {
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(2));
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (DateTimeException | NumberFormatException e) {
            throw new InvalidFormatDateException();
        }
    }

    /**
     * Making sense of the user's input and outputting the relevant commands to the input.
     * 
     * @param s The inputted user's command
     * @return A command object that executes the user's command
     * @throws InvalidFormatByeException Throws an InvalidFormatByeException when the format of Bye is incorrect.
     * @throws InvalidFormatListException Throws an InvalidFormatListException when the format of List is incorrect.
     * @throws InvalidFormatDoneException Throws an InvalidFormatDoneException when the format of Done is incorrect.
     * @throws EmptyTextException Throws an EmptyTextException when the user did not specify anything after the command.
     * @throws InvalidFormatDeleteException Throws an InvalidFormatDeleteException when the format of Delete is 
     * incorrect.
     */
    
    public static Command parse(String s) throws UnknownCommandException, InvalidFormatByeException,
            InvalidFormatListException, InvalidFormatDoneException, EmptyTextException, InvalidFormatDeleteException, InvalidFormatFindException {
        String[] inputArr = s.trim().split(" ", 2);
        inputArr[0] = inputArr[0].toLowerCase();
        if (isEnd(inputArr[0])) {
            if (inputArr.length != 1) {
                throw new InvalidFormatByeException();
            }
            return new ByeCommand(inputArr);
        } else if (isList(inputArr[0])) {
            if (inputArr.length != 1) {
                throw new InvalidFormatListException();
            }
            return new ListCommand(inputArr);
        } else if (isValidDone(inputArr[0])) {
            // checking if the input is valid
            if (inputArr.length == 1 || !isNumber(inputArr[1])) {
                throw new InvalidFormatDoneException();
            }
            return new DoneCommand(inputArr);
        } else if (isTask(inputArr[0])) {
            // checking if the input is valid
            if (inputArr.length == 1) {
                throw new EmptyTextException(inputArr[0]);
            }
            return new AddCommand(inputArr);
        } else if (isDelete(inputArr[0])) {
            // checking if the input is valid
            if (inputArr.length == 1) {
                throw new InvalidFormatDeleteException();
            }
            return new DeleteCommand(inputArr);
        } else if (isFind(inputArr[0])) {
            if (inputArr.length == 1) {
                throw new InvalidFormatFindException();
            }
            String[] inputArr2 = inputArr[1].split(" ");
            if (inputArr2.length > 1) {
                throw new InvalidFormatFindException();
            }
            return new FindCommand(inputArr);
        } else {
            return new UnknownCommand(inputArr);
        }
    }
}
