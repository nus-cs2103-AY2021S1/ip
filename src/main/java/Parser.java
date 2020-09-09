import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Parser parses a string input and turn it into a Command.
 */
public class Parser {

    static List<String> formatStringsLocalDate = Arrays.asList("M/y", "d/M/y", "M-y", "d-M-y");
    static List<String> formatStringsLocalDateTime = Arrays.asList("d-M-y HH:mm", "d/M/y HH:mm");

    private static LocalDateTime tryParseLocalDateTime(String dateTimeString) {
        for (String formatString : formatStringsLocalDateTime) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                LocalDateTime localDateAndTime = LocalDateTime.parse(dateTimeString, formatter);
                return localDateAndTime;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    private static LocalDate tryParseLocalDate(String dateString) {
        for (String formatString : formatStringsLocalDate) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                LocalDate localDate = LocalDate.parse(dateString, formatter);
                return localDate;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * Change date and time into LocalDateTime.
     *
     * @param dateAndTime String array consist of date as first element and time as second element
     * @return LocalDateTime
     * @throws NumberFormatException
     */
    public static LocalDateTime changeDateAndTime(String[] dateAndTime) throws NumberFormatException {
        assert (dateAndTime.length == 2);
        String date = dateAndTime[0];
        String time = dateAndTime[1];
        return tryParseLocalDateTime(date + " " + time);
    }

    public static LocalDate changeDate(String[] dateAndTime) throws NumberFormatException {
        assert (dateAndTime.length == 1);
        String date = dateAndTime[0];
        return tryParseLocalDate(date);
    }

    /**
     * Parse a string into Command.
     * If the first letter is not recognised, throws InvalidFirstDukeException.
     *
     * @param fullCommand String
     * @return Command
     * @throws InvalidFirstDukeException
     */
    public static Command parse(String fullCommand) throws InvalidFirstDukeException {
        String[] commandArr = fullCommand.split(" "); // split input into string array
        Command command;

        assert (commandArr.length > 0);
        switch (commandArr[0]) {
        case "bye":
            command = new Command(CommandType.EXIT_DUKE, null);
            break;
        case "list":
            command = new Command(CommandType.PRINT_ALL_TASKS, null);
            break;
        case "done":
            command = new Command(CommandType.MARK_AS_DONE, commandArr);
            break;
        case "delete":
            command = new Command(CommandType.DELETE_TASK, commandArr);
            break;
        case "todo":
            command = new Command(CommandType.ADD_TODO, commandArr);
            break;
        case "deadline":
            command = new Command(CommandType.ADD_DEADLINE, commandArr);
            break;
        case "event":
            command = new Command(CommandType.ADD_EVENT, commandArr);
            break;
        case "find":
            command = new Command(CommandType.FIND_TASK, commandArr);
            break;
        default:
            command = null;
        }

        if (command == null) {
            throw new InvalidFirstDukeException();
        }
        return command;
    }

    public static void main(String[] args) {
        try {
            String[] tempDate = {"12/2020"}; // try using date by simpledateformat instead of localDate
            String[] tempDateTime = {"24-12-2020", "18:00"};
            System.out.println(Parser.changeDateAndTime(tempDateTime));
            System.out.println(Parser.changeDate(tempDate));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
