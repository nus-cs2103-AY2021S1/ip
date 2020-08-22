import java.time.LocalDateTime;
import static java.lang.Integer.parseInt;

public class Parser {
    private static final String KEYWORD_BYE = "bye";
    private static final String KEYWORD_LIST = "list";
    private static final String KEYWORD_DONE = "done";
    private static final String KEYWORD_TODO = "todo";
    private static final String KEYWORD_EVENT = "event";
    private static final String KEYWORD_DEADLINE = "deadline";
    private static final String KEYWORD_DELETE = "delete";

    private static boolean isNumber(String str) {
        try {
            int num = parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isTODO(String type) {
        return type.equals(KEYWORD_TODO);
    }

    public static boolean isDeadline(String type) {
        return type.equals(KEYWORD_DEADLINE);
    }

    public static boolean isEvent(String type) {
        return type.equals(KEYWORD_EVENT);
    }

    private static boolean isValidDone(String type) {
        return type.equals(KEYWORD_DONE);
    }

    private static boolean isEnd(String type) {
        return type.equals(KEYWORD_BYE);
    }

    private static boolean isList(String type) {
        return type.equals(KEYWORD_LIST);
    }

    private static boolean isTask(String type) {
        return isDeadline(type) || isTODO(type) || isEvent(type);
    }

    private static boolean isDelete(String type) {
        return type.equals(KEYWORD_DELETE);
    }

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
        } catch (NumberFormatException e) {
            throw new InvalidFormatDateException();
        }
    }
    
    public static Command parse(String s) throws UnknownCommandException, InvalidFormatByeException,
            InvalidFormatListException, InvalidFormatDoneException, EmptyTextException, InvalidFormatDeleteException {
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
        } else {
            throw new UnknownCommandException();
        }
    }
}
