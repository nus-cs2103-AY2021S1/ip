public class Parser {
    public static Command parse(String input) throws UnknownCommandException {
        Command command;
        switch (input) {
            case "help":
                command = new HelpCommand();
                break;
            case "add":
                command = new AddCommand();
                break;
            case "list":
                command = new ListCommand();
                break;
            case "done":
                command = new DoneCommand();
                break;
            case "delete":
                command = new DeleteCommand();
                break;
            case "find":
                command = new FindCommand();
                break;
            case "bye":
                command = new ExitCommand();
                break;
            default:
                throw new UnknownCommandException();
        }
        return command;
    }
}
