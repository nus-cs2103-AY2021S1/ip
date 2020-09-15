package bob.parser;

import bob.commands.Command;
import bob.commands.DeadlineCommand;
import bob.commands.DeleteCommand;
import bob.commands.DoneCommand;
import bob.commands.EventCommand;
import bob.commands.ExitCommand;
import bob.commands.FindCommand;
import bob.commands.ListCommand;
import bob.commands.TodoCommand;
import bob.commands.UndoCommand;
import bob.exceptions.BobEmptyFindException;
import bob.exceptions.BobEmptyTaskException;
import bob.exceptions.BobInvalidCommandException;

/**
 * Tool to parse user input into commands.
 */
public class Parser {
    private static final int LENGTH_OF_DONE = 4;
    private static final int LENGTH_OF_DELETE = 6;
    private static final int LENGTH_OF_TODO = 4;
    private static final int LENGTH_OF_EVENT = 5;
    private static final int LENGTH_OF_DEADLINE = 8;
    private static final int LENGTH_OF_FIND = 4;

    /**
     * Handles user input which decides which command Bob executes.
     *
     * @param input User input.
     * @return Updated Bob.
     * @throws BobInvalidCommandException If user input is not recognised.
     * @throws BobEmptyTaskException If there is no task description.
     * @throws BobEmptyFindException If there is no input for the search.
     */
    public static Command parse(String input)
            throws BobInvalidCommandException, BobEmptyTaskException, BobEmptyFindException {
        boolean isExitCommand = input.equals("bye");
        boolean isListCommand = input.equals("list");
        boolean isDoneCommand = input.length() >= LENGTH_OF_DONE && input.startsWith("done");
        boolean isDeleteCommand = input.length() >= LENGTH_OF_DELETE && input.startsWith("delete");
        boolean isTodoCommmand = input.length() >= LENGTH_OF_TODO && input.startsWith("todo");
        boolean isEventCommand = input.length() >= LENGTH_OF_EVENT && input.startsWith("event");
        boolean isDeadlineCommand = input.length() >= LENGTH_OF_DEADLINE && input.startsWith("deadline");
        boolean isFindCommand = input.length() >= LENGTH_OF_FIND && input.startsWith("find");
        boolean isUndoCommand = input.startsWith("undo");
        boolean isUnknownCommand = !(isExitCommand || isListCommand || isDoneCommand || isDeleteCommand
                || isTodoCommmand || isEventCommand || isDeadlineCommand || isFindCommand || isUndoCommand);

        if (isExitCommand) {
            return new ExitCommand();
        } else if (isListCommand) {
            return new ListCommand();
        } else if (isDoneCommand) {
            return new DoneCommand(input.substring(4));
        } else if (isDeleteCommand) {
            return new DeleteCommand(input.substring(6));
        } else if (isTodoCommmand) {
            return new TodoCommand(input.substring(4));
        } else if (isEventCommand) {
            return new EventCommand(input.substring(5));
        } else if (isDeadlineCommand) {
            return new DeadlineCommand(input.substring(8));
        } else if (isFindCommand) {
            return new FindCommand(input.substring((4)));
        } else if (isUndoCommand) {
            return new UndoCommand();
        } else {
            assert isUnknownCommand;
            throw new BobInvalidCommandException();
        }
    }
}
