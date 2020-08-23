public class Parser {

    protected Command parseCommand(String userCommand) throws InvalidCommandException {
        String[] command = userCommand.split(" ");
        if (command[0].equals("done")) {
            return new DoneCommand(userCommand);
        } else if (command[0].equals("todo")) {
            return new TodoCommand(userCommand);
        } else if (command[0].equals("event")) {
            return new EventCommand(userCommand);
        } else if (command[0].equals("deadline")) {
            return new DeadlineCommand(userCommand);
        } else if (command[0].equals("delete")) {
            return new DeleteCommand(userCommand);
        } else if (command[0].equals("list")) {
            return new ListCommand(userCommand);
        } else if (command[0].equals("bye")) {
            return new ByeCommand();
        } else if (command[0].equals("find")) {
            return new FindCommand(userCommand);
        } else {
            throw new InvalidCommandException();
        }
    }
}
