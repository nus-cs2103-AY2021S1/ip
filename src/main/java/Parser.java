import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final List<String> DATE_FORMATS = Arrays.asList("yyyy/MM/dd HHmm", "y/M/d HHmm", "y-M-d HHmm");
    public static Command parse(String userInput) throws DukeException {
        int i = userInput.trim().indexOf(' ');
        String command = userInput;
        String detail = "";
        if (i > 0) {
            command = userInput.substring(0, i);
            detail = userInput.substring(i).trim();
        }
        switch (command) {
        case ("bye") :
            return new ByeCommand();
        case ("list") :
            return new ListCommand();
        case ("todo") :
            return new AddCommand(parseTodo(detail));
        case ("deadline") :
            return new AddCommand(parseDeadline(detail));
        case ("event") :
            return new AddCommand(parseEvent(detail));
        case ("delete") :
            return new DeleteCommand(parseNumber(detail));
        case ("done") :
            return new DoneCommand(parseNumber(detail));
        case ("find") :
            return new FindCommand(detail);
        default:
            throw new UnexpectedInputException();
        }
    }

    private static DateAndTime parseTime(String timeFormat) {
        if (!timeFormat.contains(" ")) {
            LocalDate localDate = LocalDate.parse(timeFormat.trim());
            return new DateAndTime(localDate);
        } else {
            String[] split = timeFormat.trim().split(" ");
            LocalTime localTime = LocalTime.parse(split[0].trim());
            LocalDate localDate = LocalDate.parse(split[1].trim());
            return new DateAndTime(localDate, localTime);
        }
    }

    private static ToDoTask parseTodo(String detail) throws DukeException {
        if (detail.equals("")) {
            throw new MissingDescriptionException(TaskType.TODO);
        } else {
            return new ToDoTask(detail, false);
        }
    }

    private static DeadlineTask parseDeadline(String detail) throws DukeException {
        if (detail.equals("")) {
            throw new MissingDescriptionException(TaskType.DEADLINE);
        } else {
            String descriptionAndTime = detail.replace("/by", "%");
            String description = descriptionAndTime.split("%")[0];
            String time = descriptionAndTime.split("%")[1];
            DateAndTime dateAndTime = parseTime(time.trim());
            return new DeadlineTask(description, false, dateAndTime);
        }
    }

    private static EventTask parseEvent(String detail) throws DukeException {
        if (detail.equals("")) {
            throw new MissingDescriptionException(TaskType.EVENT);
        } else {
            String descriptionAndTime = detail.replace("/at", "%");
            String description = descriptionAndTime.split("%")[0];
            String time = descriptionAndTime.split("%")[1];
            DateAndTime dateAndTime = parseTime(time.trim());
            return new EventTask(description, false, dateAndTime);
        }
    }

    private static int parseNumber(String detail) throws DukeException {
        if (detail.trim().length() == 0) {
            throw new MissingTaskIndexException();
        }
        try {
            return Integer.parseInt(detail.trim());
        } catch (NumberFormatException e) {
            throw new InvalidNumberInput();
        }
    }
}
