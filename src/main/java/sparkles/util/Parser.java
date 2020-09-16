package sparkles.util;

import sparkles.command.Command;
import sparkles.command.DeleteCommand;
import sparkles.command.DoneCommand;
import sparkles.command.ExitCommand;
import sparkles.command.FindCommand;
import sparkles.command.ShowListCommand;
import sparkles.command.TaskUnclearCommand;
import sparkles.command.addcommand.AddCommand;
import sparkles.command.addcommand.AddDeadlineCommand;
import sparkles.command.addcommand.AddEventCommand;
import sparkles.command.addcommand.AddTodoCommand;

/**
 * A Parser class that deals and make sense of user commands.
 */
public class Parser {

    /**
     * Taking in user's input as one String and return the correct Command class to execute.
     *
     * @param command user's input
     * @return corresponding Command
     */
    public static Command parse(String command) {
        return parseCommand(command);
    }

    private static Command parseCommand(String command) {
        String lowerCase = command.toLowerCase();

        if (lowerCase.equals("list")) {
            return new ShowListCommand(command);
        } else if (lowerCase.startsWith("done")) {
            return new DoneCommand(command);
        } else if (lowerCase.equals("bye")) {
            return new ExitCommand(command);
        } else if (lowerCase.startsWith("delete")) {
            return new DeleteCommand(command);
        } else if (lowerCase.startsWith("find")) {
            return new FindCommand(command);
        } else {
            return new AddCommand(command);
        }
    }

    /**
     * Taking in user's input as one String and return the correct AddCommand class to execute.
     *
     * @param command user's input
     * @return corresponding AddCommand
     */
    public static Command parseAddCommand(String command) {
        String lowerCase = command.toLowerCase();

        if (lowerCase.startsWith("deadline")) {
            return new AddDeadlineCommand(command);
        } else if (lowerCase.startsWith("event")) {
            return new AddEventCommand(command);
        } else if ((lowerCase.startsWith("todo"))) {
            return new AddTodoCommand(command);
        } else {
            return new TaskUnclearCommand(command);
        }
    }
}
