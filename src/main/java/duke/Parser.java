package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommand;

/**
 * Parses user commands in the Duke program.
 */
public class Parser {

    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String FIND = "find";

    /**
     * Parses user commands and returns the appropriate executable command for the program to then execute.
     *
     * @param fullCommand The user command to be parsed.
     * @return The appropriate command to be executed.
     */
    public static Command parse(String fullCommand) {
        String[] commandWords = fullCommand.split(" ");
        String firstWord = commandWords[0];
        if (fullCommand.equals(BYE)) { // Exit the program
            return new ExitCommand();
        } else if (fullCommand.equals(LIST)) { // List out task list
            return new ListCommand();
        } else if (firstWord.equals(DONE)) { // Done with a task
            return new DoneCommand(commandWords);
        } else if (firstWord.equals(DELETE)) { // Delete a task
            return new DeleteCommand(commandWords);
        } else if (firstWord.equals(TODO)) { // Add To-Do task
            return new AddTodoCommand(fullCommand);
        } else if (firstWord.equals(EVENT)) { // Add duke.task.Event task
            return new AddEventCommand(fullCommand);
        } else if (firstWord.equals(DEADLINE)) { // Add duke.task.Deadline task
            return new AddDeadlineCommand(fullCommand);
        } else if (firstWord.equals(FIND)) { // Find task(s) in task list
            return new FindCommand(fullCommand);
        } else { // Unknown command entered
            return new UnknownCommand();
        }
    }
}
