package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.NoCommand;
import duke.task.TaskType;

/**
 * The Parser class will deal with making sense of the user command
 */
public class Parser {
    /**
     * Reads the input to determine which type of command Duke
     * should execute to reply the user.
     *
     * @param input The user input entered in the command line.
     * @return The Command to be executed.
     * @throws DukeException if the input is formatted wrongly.
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        String inputCommand = inputArray[0];
        String inputDetails;
        String[] taskDetails;
        String taskName, taskTime;
        switch (inputCommand) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
                throw new DukeException("Which task do you want to mark as done?");
            }
            inputDetails = inputArray[1].trim();
            return new DoneCommand(inputDetails);
        case "delete":
            if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
                throw new DukeException("Which task do you want to delete?");
            }
            inputDetails = inputArray[1].trim();
            return new DeleteCommand(inputDetails);
        case "find":
            if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
                throw new DukeException("Which task do you want to find?");
            }
            inputDetails = inputArray[1].trim();
            return new FindCommand(inputDetails);
        case "todo":
            if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            inputDetails = inputArray[1].trim();
            taskName = inputDetails;
            taskTime = "";
            return new AddCommand(TaskType.TODO, taskName, taskTime);
        case "deadline":
            if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            inputDetails = inputArray[1].trim();
            taskDetails = inputDetails.split(" /by ");
            if (taskDetails.length != 2) {
                throw new DukeException("Please use the format: deadline (name) /by "
                        + "(yyyy-mm-dd)");
            }
            taskName = taskDetails[0];
            taskTime = taskDetails[1];
            return new AddCommand(TaskType.DEADLINE, taskName, taskTime);
        case "event":
            if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
                throw new DukeException("The description of a event cannot be empty.");
            }
            inputDetails = inputArray[1].trim();
            taskDetails = inputDetails.split(" /at ");
            if (taskDetails.length != 2) {
                throw new DukeException("Please use the format: event (name) /at "
                        + "(yyyy-mm-dd)");
            }
            taskName = taskDetails[0];
            taskTime = taskDetails[1];
            return new AddCommand(TaskType.EVENT, taskName, taskTime);
        default:
            return new NoCommand();
        }
    }
}
