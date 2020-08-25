package duke;

import duke.command.*;

/**
 * Parses user commands in the Duke program.
 */
public class Parser {

    /**
     * Parses user commands and returns the appropriate executable command for the program to then execute.
     *
     * @param fullCommand The user command to be parsed.
     * @return The appropriate command to be executed.
     */
    public static Command parse(String fullCommand) {
        String[] commandWords = fullCommand.split(" ");
        if (fullCommand.equals("bye")) { // Exit the program
            return new ExitCommand();
        } else if (fullCommand.equals("list")) { // List out task list
            return new ListCommand();
        } else if (commandWords[0].equals("done")) { // Done with a task
            return new DoneCommand(commandWords);
        } else if (commandWords[0].equals("delete")) { // Delete a task
            return new DeleteCommand(commandWords);
        } else if (commandWords[0].equals("todo")) { // Add To-Do task
            return new AddTodoCommand(fullCommand);
        } else if (commandWords[0].equals("event")) { // Add duke.task.Event task
            return new AddEventCommand(fullCommand);
        } else if (commandWords[0].equals("deadline")) { // Add duke.task.Deadline task
            return new AddDeadlineCommand(fullCommand);
        } else if (commandWords[0].equals("find")) { // Find task(s) in task list
            return new FindCommand(fullCommand);
        } else { // Unknown command entered
            return new UnknownCommand();
        }
    }
}
