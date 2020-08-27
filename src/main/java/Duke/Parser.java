package Duke;

import Duke.Command.*;
import Duke.Exception.*;

public class Parser {
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.length() >= 4 && input.startsWith("done")) {
            return new DoneCommand(input.substring(4));
        } else if (input.length() >= 6 && input.startsWith("delete")) {
            return new DeleteCommand(input.substring(6));
        } else if (input.length() >= 4 && input.startsWith("todo")) {
            return new TodoCommand(input.substring(4));
        } else if (input.length() >= 5 && input.startsWith("event")) {
            return new EventCommand(input.substring(5));
        } else if (input.length() >= 8 && input.startsWith("deadline")) {
            return new DeadlineCommand(input.substring(8));
        } else {
            throw new InvalidInputException();
        }
    }
}
