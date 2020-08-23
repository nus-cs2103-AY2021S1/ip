public class Parser {
    public static Command parse(String fullCommand) {
        Command command;

        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            command = new ListCommand();
        } else if (fullCommand.startsWith("done")) {
            command = new DoneCommand(Integer.parseInt(fullCommand.split(" ")[1]));
        } else if (fullCommand.startsWith("delete")) {
            command = new DeleteCommand(Integer.parseInt(fullCommand.split(" ")[1]));
        } else if (fullCommand.startsWith("todo")) {
            command = new AddCommand("todo", fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            command = new AddCommand("deadline", fullCommand);
        } else if (fullCommand.startsWith("event")) {
            command = new AddCommand("event", fullCommand);
        } else {
            command = new UnknownCommand(fullCommand);
        }
        return command;
    }
}
