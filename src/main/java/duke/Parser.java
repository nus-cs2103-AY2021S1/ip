package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.CompleteTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.FindTaskCommand;
import duke.command.ShowListCommand;

/**
 * Parser class that deals with making sense of the user command
 */
public class Parser {

    /**
     * Static method that takes in the user's command and return a Command object.
     * If no command matches, it will throw a DukeException
     *
     * @param command input from the user
     * @return a Command object
     */
    public static Command parse(String command) throws DukeException {
        String[] arr = command.split(" ");
        String keyWord = arr[0];

        assert keyWord.length() == 0 : "Not valid";

        if (keyWord.equals("bye")) {
            return new ExitCommand(command);
        } else if (keyWord.equals("list")) {
            return new ShowListCommand(command);
        } else if (keyWord.equals("todo")) {
            return new AddTodoCommand(command);
        } else if (keyWord.equals("deadline")) {
            return new AddDeadlineCommand(command);
        } else if (keyWord.equals("event")) {
            return new AddEventCommand(command);
        } else if (keyWord.equals("done")) {
            return new CompleteTaskCommand(command);
        } else if (keyWord.equals("delete")) {
            return new DeleteTaskCommand(command);
        } else if (keyWord.equals("find")) {
            return new FindTaskCommand(command);
        } else {
            throw new DukeException("☹ OOPS!!! wait..... I don't understand your order my sir.");
        }
    }
}
