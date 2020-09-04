public class CommandParser {

    public static Command parse(String command) throws DukeException {
        if (command.startsWith("bye")) {
            return new ExitCommand(command);
        }if (command.startsWith("list")) {
            return new ListCommand(command);
        } else if (command.startsWith("done")) {
            return new DoneCommand(command);
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(command);
        } else if (command.startsWith("deadline") || command.startsWith("event") || (command.startsWith("todo"))) {
            return new AddCommand(command);
        } else {
            throw new DukeException("Command not recognised!");
        }
    }
}
