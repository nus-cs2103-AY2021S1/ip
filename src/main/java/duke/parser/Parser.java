package duke.parser;

import duke.command.AddCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.Command;
import duke.task.Task;

/**
 * Represents an object that deals with making sense of the user command.
 */
public class Parser {

    /**
     * Returns a <code>Command</code> object based on what the user command is.
     * e.g. if user inputs <code>done 1</code>, a <code>DoneCommand</code> object
     * is created with the user input as the parameter.
     *
     * @param userInput User input that is typed into the command line.
     * @return <code>Command</code> object based on user input.
     */
    public static Command parse(String userInput) {
        Task t = new Task(userInput);

        if (userInput.equals("list")) {
            return new ListCommand(userInput);
        } else if (t.getFirstWord().equals("done")) {
            return new DoneCommand(userInput);
        } else if (t.getFirstWord().equals("delete")) {
            return new DeleteCommand(userInput);
        } else if (userInput.equals("bye")) {
            return new ExitCommand(userInput);
        } else {
            return new AddCommand(userInput);
        }
    }
}

