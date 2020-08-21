public class Parser {
    public static Command parse(String input) throws DukeException {
        switch (input.split(" ")[0].toLowerCase()) {
        case "":
            throw new DukeException("Please type a command");
        case "bye":
            return new ExitCommand();
        case "clear":
            return new ClearCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(input.substring(5)) - 1);
        case "delete":
            return new DeleteCommand(Integer.parseInt(input.substring(7)) - 1);
        case "deadline":
            try {
                int by = input.indexOf(" /by");
                return new DeadlineCommand(input.substring(9, by), input.substring(by + 5));
            } catch (Exception e) {
                throw new DukeException("Deadline format isn't correct");
            }
        case "event":
            try {
                int at = input.indexOf(" /at");
                return new EventCommand(input.substring(6, at), input.substring(at + 5));
            } catch (Exception e) {
                throw new DukeException("Event format isn't correct");
            }
        case "todo":
            return new TodoCommand(input.substring(5));
        default:
            throw new DukeException("I don't know what that means :( ");
        }
    }
}