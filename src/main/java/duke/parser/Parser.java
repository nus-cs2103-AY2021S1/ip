package duke.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CompleteCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.TaskType;

/**
 * Encapsulates the parsing of user inputs.
 */
public class Parser {
    private static final Set<String> COMMAND_ADD_TODO = new HashSet<>(Arrays.asList("todo", "t"));
    private static final Set<String> COMMAND_ADD_EVENT = new HashSet<>(Arrays.asList("event", "e"));
    private static final Set<String> COMMAND_ADD_DEADLINE = new HashSet<>(Arrays.asList("deadline", "d"));
    private static final Set<String> COMMAND_LIST = new HashSet<>(Arrays.asList("list", "ls", "l"));
    private static final Set<String> COMMAND_COMPLETE = new HashSet<>(Arrays.asList("check", "ck"));
    private static final Set<String> COMMAND_DELETE = new HashSet<>(Arrays.asList("remove", "rm"));
    private static final Set<String> COMMAND_FIND = new HashSet<>(Arrays.asList("find", "f"));
    private static final Set<String> COMMAND_EXIT = new HashSet<>(Arrays.asList("bye", "exit", "q"));

    /**
     * Processes the full user input.
     *
     * @param fullCommand user's input
     * @return one of the subclasses of Command that represents the application logic
     * @throws UnknownCommandException if the user's command is unknown
     */
    public static Command parse(String fullCommand) throws UnknownCommandException {
        StringBuilder commandInput = new StringBuilder();
        StringBuilder argsInput = new StringBuilder();
        boolean isCommandFound = false;
        for (int i = 0; i < fullCommand.length(); i++) {
            if (isCommandFound) {
                argsInput.append(fullCommand.charAt(i));
            } else if (fullCommand.charAt(i) == ' ') {
                isCommandFound = true;
            } else {
                commandInput.append(fullCommand.charAt(i));
            }
        }

        String command = commandInput.toString();
        String args = argsInput.toString();

        if (COMMAND_ADD_DEADLINE.contains(command)) {
            return new AddCommand(args, TaskType.DEADLINE);
        } else if (COMMAND_ADD_EVENT.contains(command)) {
            return new AddCommand(args, TaskType.EVENT);
        } else if (COMMAND_ADD_TODO.contains(command)) {
            return new AddCommand(args, TaskType.TODO);
        } else if (COMMAND_COMPLETE.contains(command)) {
            return new CompleteCommand(args);
        } else if (COMMAND_DELETE.contains(command)) {
            return new DeleteCommand(args);
        } else if (COMMAND_EXIT.contains(command)) {
            return new ExitCommand(args);
        } else if (COMMAND_LIST.contains(command)) {
            return new ListCommand(args);
        } else if (COMMAND_FIND.contains(command)) {
            return new FindCommand(args);
        } else {
            throw new UnknownCommandException("Unknown command");
        }
    }
}
