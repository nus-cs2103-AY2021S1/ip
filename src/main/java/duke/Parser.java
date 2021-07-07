package duke;

/**
 * Encapsulates Parser functionality to parse user input
 */
public class Parser {
    /**
     * Splits user input into command and description to facilitate processing.
     *
     * @param fullCommand full user input
     * @return the appropriate Command
     * @throws DukeException if user input is invalid
     */
    static Command parse(String fullCommand) throws DukeException {
        String[] parsedCommand = fullCommand.split(" ", 2); // separates the first word from the rest
        String command = parsedCommand[0].toLowerCase();
        return callCommand(command, parsedCommand);
    }

    /**
     * Invokes the correct Command object based on the user's command.
     *
     * @param command user's command input
     * @param parsedCommand user's parsed input
     * @return the appropriate duke.Command
     * @throws DukeException if user input is invalid
     */
    static Command callCommand(String command, String[] parsedCommand) throws DukeException {
        if (command.equals(UserCommand.BYE.getCommand())) {
            return new ExitCommand(parsedCommand);
        } else if (command.equals(UserCommand.LIST.getCommand())
                || command.equals(UserCommand.LS.getCommand())) {
            return new ListCommand(parsedCommand);
        } else if (command.equals(UserCommand.DONE.getCommand())) {
            return new DoneCommand(parsedCommand);
        } else if (command.equals(UserCommand.TODO.getCommand())
                || command.equals(UserCommand.T.getCommand())) {
            return new AddToDoCommand(parsedCommand);
        } else if (command.equals(UserCommand.DEADLINE.getCommand())
                || command.equals(UserCommand.D.getCommand())) {
            return new AddDeadlineCommand(parsedCommand);
        } else if (command.equals(UserCommand.EVENT.getCommand())
                || command.equals(UserCommand.E.getCommand())) {
            return new AddEventCommand(parsedCommand);
        } else if (command.equals(UserCommand.DELETE.getCommand())) {
            return new DeleteCommand(parsedCommand);
        } else if (command.equals(UserCommand.CLEAR.getCommand())) {
            return new ClearCommand(parsedCommand);
        } else if (command.equals(UserCommand.FIND.getCommand())) {
            return new FindCommand(parsedCommand);
        } else {
            throw new DukeException("NANI??! Please say something that I can understand!\n");
        }
    }
}
