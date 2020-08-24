package Duke.Helpers;
import Duke.Commands.*;
import Duke.Errors.DukeException;
import Duke.Errors.WrongInputException;

public class Parser {
    public static Command parse(String string) throws DukeException {
        if (string.length() >= 3 && string.equals("bye")) {
            return new ExitCommand(string);
        }else if (string.length() >= 4 && string.equals("list")) {
            return new ListCommand(string);
        }else if (string.length() >= 6 && string.substring(0, 6).equals("delete")) {
            return new DeleteCommand(string);
        }else if (string.length() >= 4 && string.substring(0, 4).equals("done")) {
            return new DoneCommand(string);
        }else if (string.length() >= 4 && string.substring(0, 4).equals("todo")) {
            return new TodoCommand(string);
        }else if (string.length() >= 5 && string.substring(0, 5).equals("event")) {
            return new EventCommand(string);
        }else if (string.length() >= 8 && string.substring(0, 8).equals("deadline")) {
            return new DeadlineCommand(string);
        }else{
            return new RandomCommand(string);
        }
    }
}
