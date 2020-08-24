public class CommandHandler {

    public static Command parseCommand(String cmd) {
        switch (cmd) {
            case "bye":
            return new ExitCommand();
            case "list":
            return new ListCommand();
            default:
            return new ErrorCommand();
        }
    }
}