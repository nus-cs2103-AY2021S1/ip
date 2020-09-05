/**
 * Class to handle user commands.
 * @author vanGoghhh
 */

public class Parser {

    /**
     * Sorts user commands and handle them according to their types.
     * @param userCommand input command by the user.
     * @return the types of Command depending on the user's commands.
     * @throws InvalidCommandException when a non matching command is entered.
     */
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
        } else if (command[0].equals("sort")) {
            return new SortCommand(userCommand);
        }
        else {
            throw new InvalidCommandException();
        }
    }
}
