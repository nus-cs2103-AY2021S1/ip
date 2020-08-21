import java.time.format.DateTimeParseException;

public class Parser {

    static Command parse(String fullCommand) throws DukeEmptyInputException, DukeInvalidDateTimeException, DukeInvalidCommandException {
        String[] commandArr = fullCommand.trim().split(" ", 2);
        switch(commandArr[0]) {
            case "bye":
                return parseBye();
            case "list":
                return parseList();
            case "done":
                return parseDone(Integer.parseInt(commandArr[1]));
            case "delete":
                return parseDelete(Integer.parseInt(commandArr[1]));
            case "todo":
                return parseToDo(commandArr[1]);
            case "deadline":
                return parseDeadline(commandArr[1]);
            case "event":
                return parseEvent(commandArr[1]);
            default:
                throw new DukeInvalidCommandException("Unknown command.");
        }
    }

    static ByeCommand parseBye() {
        return new ByeCommand();
    }

    static ListCommand parseList() {
        return new ListCommand();
    }

    static DoneCommand parseDone(int taskNo) {
        return new DoneCommand(taskNo);
    }

    static DeleteCommand parseDelete(int taskNo) {
        return new DeleteCommand(taskNo);
    }

    static AddCommand parseToDo(String description) throws DukeEmptyInputException {
        if (description.length() == 0) {
            throw new DukeEmptyInputException("The description of a todo cannot be empty.");
        }
        return new AddCommand(new Todo(description));
    }

    static AddCommand parseDeadline(String description) throws DukeEmptyInputException, DukeInvalidDateTimeException {
        String[] details = description.split(" /by ");
        if (details[0].length() == 0) {
            throw new DukeEmptyInputException("The description of a deadline cannot be empty.");
        }
        if (details.length <= 1 || details[1].length() == 0) {
            throw new DukeEmptyInputException("The due date of a deadline cannot be empty.");
        }
        try {
            return new AddCommand(new Deadline(details[0], details[1]));
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateTimeException("The date for your deadline cannot be parsed.");
        }
    }

    static AddCommand parseEvent(String description) throws DukeEmptyInputException, DukeInvalidDateTimeException {
        String[] details = description.split(" /at ");
        if (details[0].length() == 0) {
            throw new DukeEmptyInputException("The description of a event cannot be empty.");
        }
        if (details.length <= 1 || details[1].length() == 0) {
            throw new DukeEmptyInputException("The date of an event cannot be empty.");
        }
        try {
            return new AddCommand(new Event(details[0], details[1]));
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateTimeException("The date for your event cannot be parsed.");
        }
    }

}
