package duke;


import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.FindCommand;
import command.PrintListCommand;
import command.UnknownCommand;


/**
 * A class to deals with making sense of the input command.
 */
public class Parser {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String TODO_COMMAND = "todo";
    private static final String FIND_COMMAND = "find";
    /**
     * A function to parse the input from the user.
     * @param fullCommand The input of the user which is saved in String.
     * @return Command which is grouped into different command.
     */
    public static Command parse (String fullCommand) {
        String[] parsed = fullCommand.split(" " , 2);
        if (parsed[0].equals(LIST_COMMAND)) {
            return new PrintListCommand(parsed);
        } else if (parsed[0].equals(DONE_COMMAND)) {
            return new DoneCommand(parsed);
        } else if (parsed[0].equals(DELETE_COMMAND)) {
            return new DeleteCommand(parsed);
        } else if (parsed[0].equals(EVENT_COMMAND)) {
            return new AddEventCommand(parsed);
        } else if (parsed[0].equals(DEADLINE_COMMAND)) {
            return new AddDeadlineCommand(parsed);
        } else if (parsed[0].equals(TODO_COMMAND)) {
            return new AddTodoCommand(parsed);
        } else if (parsed[0].equals(BYE_COMMAND)) {
            return new ExitCommand(parsed);
        } else if (parsed[0].equals(FIND_COMMAND)) {
            return new FindCommand(parsed);
        } else {
            return new UnknownCommand(parsed);
        }

    }

}
