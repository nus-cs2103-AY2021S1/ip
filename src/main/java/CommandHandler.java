public class CommandHandler {

    public static Command parseCommand(String cmd) {
        switch (cmd) {
            case "bye":
            return new ExitCommand();
            case "list":
            return new ListCommand();
            case "todo":
            return new TodoCommand();
            case "deadline":
            return new DeadlineCommand();
            case "event":
            return new EventCommand();
            case "complete":
            return new CompleteCommand();
            case "delete":
            return new DeleteCommand();
            default:
            return new ErrorCommand();
        }
    }
}