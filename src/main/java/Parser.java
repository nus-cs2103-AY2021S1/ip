import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String textCommand) throws EmptyBodyException, UnknownInputException {
        String[] words = textCommand.split(" ", 2);
        String firstWord = words[0];
        String remaining = words.length > 1 ? words[1] : "";

        switch (firstWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "delete": {
            if (remaining.equals("")) {
                throw new EmptyBodyException("task number", "task");
            }
            int taskNumber = Integer.parseInt(remaining);
            return new DeleteCommand(taskNumber);
        }
        case "done": {
            if (remaining.equals("")) {
                throw new EmptyBodyException("task number", "task");
            }
            int taskNumber = Integer.parseInt(remaining);
            return new DoneCommand(taskNumber);
        }
        case "todo": {
            return new AddCommand("todo", remaining);
        }
        case "deadline": {
            return new AddCommand("deadline", remaining);
        }
        case "event": {
            return new AddCommand("event", remaining);
        }
        case "find": {
            return new FindCommand(remaining);
        }
        default:
            throw new UnknownInputException(firstWord);
        }
    }
}
