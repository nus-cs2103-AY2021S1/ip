public class CommandParser {

    public static Command parse(String command) throws DukeException {
        String[] extractedCommand = command.split(" ", 2);
        switch(extractedCommand[0]) {
        case("bye"):
            return new ExitCommand(command);
        case("list"):
            return new ListCommand(command);
        case("done"):
            return new DoneCommand(command);
        case("delete"):
            return new DeleteCommand(command);
        case("deadline"):
        case("event"):
        case("todo"):
            return new AddCommand(command);
        default:
            throw new DukeException("Command not recognised!");
        }
    }
}
