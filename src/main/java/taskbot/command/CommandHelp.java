package taskbot.command;

import taskbot.exceptions.InvalidCommandException;

/**
 * This class gets the instruction from the specified command.
 */
public class CommandHelp {
    /**
     * Gets help on the specific command given.
     *
     * @param command The command to retrieve instructions for.
     * @return The relevant command's instruction.
     * @throws InvalidCommandException if input command does not match any known command types.
     */
    public static String getCommandInstruction(String command) throws InvalidCommandException {
        switch (command) {
        case "help":
            return HelpCommand.getInstruction();
        case "todo":
            return TodoCommand.getInstruction();
        case "deadline":
            return DeadlineCommand.getInstruction();
        case "event":
            return EventCommand.getInstruction();
        case "list":
            return ListCommand.getInstruction();
        case "upcoming":
            return UpcomingCommand.getInstruction();
        case "find":
            return FindCommand.getInstruction();
        case "done":
            return DoneCommand.getInstruction();
        case "delete":
            return DeleteCommand.getInstruction();
        case "bye":
            return ExitCommand.getInstruction();
        default:
            throw new InvalidCommandException("That was not a valid command.\nPlease try again.");
        }
    }
}
