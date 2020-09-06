package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CompleteCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.TaskType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Encapsulates the parsing of user inputs.
 */
public class Parser {
    private static final Set<String> COMMAND_EXIT = new HashSet<>(Arrays.asList("bye", "exit", "q"));
    private static final Set<String> COMMAND_LIST = new HashSet<>(Arrays.asList("list", "ls"));
    private static final Set<String> COMMAND_FIND = new HashSet<>(Arrays.asList("find", "search"));
    private static final Set<String> COMMAND_COMPLETE = new HashSet<>(Arrays.asList("done", "complete"));
    private static final Set<String> COMMAND_ADD_TODO = new HashSet<>(Collections.singletonList("todo"));
    private static final Set<String> COMMAND_ADD_EVENT = new HashSet<>(Collections.singletonList("event"));
    private static final Set<String> COMMAND_ADD_DEADLINE = new HashSet<>(Collections.singletonList("deadline"));
    private static final Set<String> COMMAND_DELETE = new HashSet<>(Arrays.asList("delete", "remove"));

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

        if (command.has(COMMAND_ADD_DEADLINE)) {
            return new AddCommand(args, TaskType.DEADLINE);
        } else if (command.equals(COMMAND_ADD_EVENT)) {
            return new AddCommand(args, TaskType.EVENT);
        } else if (command.equals(COMMAND_ADD_TODO)) {
            return new AddCommand(args, TaskType.TODO);
        } else if (command.equals(COMMAND_COMPLETE)) {
            return new CompleteCommand(args);
        } else if (command.equals(COMMAND_DELETE)) {
            return new DeleteCommand(args);
        } else if (command.equals(COMMAND_EXIT)) {
            return new ExitCommand(args);
        } else if (command.equals(COMMAND_LIST)) {
            return new ListCommand(args);
        } else if (command.equals(COMMAND_FIND)) {
            return new FindCommand(args);
        } else {
            throw new UnknownCommandException("Unknown command");
        }
    }
}
