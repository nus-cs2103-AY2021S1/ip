public class Parser {
    static Command parse(String fullCommand) throws DukeException {
        String[] parsedCommand = fullCommand.split(" ", 2); // separates the first word from the rest
        String command = parsedCommand[0].toLowerCase();
        return callCommand(command, parsedCommand);
    }

    static Command callCommand(String command, String[] parsedCommand) throws DukeException {
        if (command.equals(UserCommand.BYE.getCommand())) {
            return new ExitCommand(parsedCommand);
        } else if (command.equals(UserCommand.LIST.getCommand())) {
            return new ListCommand(parsedCommand);
        } else if (command.equals(UserCommand.DONE.getCommand())) {
            return new DoneCommand(parsedCommand);
        } else if (command.equals(UserCommand.TODO.getCommand())) {
            return new AddToDoCommand(parsedCommand);
        } else if (command.equals(UserCommand.DEADLINE.getCommand())) {
            return new AddDeadlineCommand(parsedCommand);
        } else if (command.equals(UserCommand.EVENT.getCommand())) {
            return new AddEventCommand(parsedCommand);
        } else if (command.equals(UserCommand.DELETE.getCommand())) {
            return new DeleteCommand(parsedCommand);
        } else {
            throw new DukeException("NANI??! Please say something that I can understand!\n");
        }
    }
}
