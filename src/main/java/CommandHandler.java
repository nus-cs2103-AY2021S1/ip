public class CommandHandler {

    public static Command parseCommand(String cmd) {
        switch (cmd) {
            case "bye":
            return new ExitCommand();
            case "list":
            return new ListCommand();
            case "todo":
            return new TodoCommand();
            default:
            return new ErrorCommand();
        }
    }
}