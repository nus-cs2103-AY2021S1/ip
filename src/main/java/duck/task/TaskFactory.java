package duck.task;

import duck.Option;
import duck.Parser;
import duck.exception.DuckException;

public class TaskFactory {
    public static Task createTaskFromInput(String input) throws DuckException {
        Option option = Parser.parseOption(input);
        String inputWithoutOption;
        switch (option) {
        case TODO:
            inputWithoutOption = input.substring(5).strip();
            return createTodo(inputWithoutOption);
        case DEADLINE:
            inputWithoutOption = input.substring(9).strip();
            return createDeadline(inputWithoutOption);
        case EVENT:
            inputWithoutOption = input.substring(6).strip();
            return createEvent(inputWithoutOption);
        default:
            throw new DuckException("Invalid task type!");
        }
    }

    private static Todo createTodo(String inputWithoutOption) throws DuckException {
        String description = Parser.parseDescription(inputWithoutOption);
        return new Todo(description);
    }

    private static Deadline createDeadline(String inputWithoutOption) throws DuckException {
        String description = Parser.parseDescription(inputWithoutOption);
        String date = Parser.parseDate(inputWithoutOption);
        return new Deadline(description, date);
    }

    private static Event createEvent(String inputWithoutOption) throws DuckException {
        String description = Parser.parseDescription(inputWithoutOption);
        String date = Parser.parseDate(inputWithoutOption);
        return new Event(description, date);
    }
}
