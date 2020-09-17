package duke;

/**
 * The Parser class deals with making sense of the user command.
 */
public class Parser {

    public static Command giveReminders() {
        return new ReminderCommand();
    }

    /**
     * Reads the user input and determines which command to execute.
     *
     * @param command User input.
     * @return Command The type of command to execute.
     * @throws DukeException
     */
    public static Command parse(String command) throws DukeException {
        String[] inputArray = command.split(" ", 2);
        String inputCommand = inputArray[0];
        String inputDetails = inputArray[1];
        String[] taskParams;
        switch (inputCommand) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "delete":
            if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
                throw new DukeException("Which task do you want to delete?");
            }
            Integer indexOfDeletedTask = Integer.parseInt(inputDetails.trim());
            return new CompleteCommand(indexOfDeletedTask);
        case "done":
            if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
                throw new DukeException("Which task do you want to complete?");
            }
            Integer indexOfCompletedTask = Integer.parseInt(inputDetails.trim());
            return new CompleteCommand(indexOfCompletedTask);
        case "find":
            if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
                throw new DukeException("Which task do you want to find?");
            }
            return new FindCommand(inputDetails.trim());
        case "todo":
            if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
                throw new DukeException("Please enter the description of your todo");
            }
            return new AddCommand(TaskType.TODO, inputDetails.trim());
        case "deadline":
            if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
                throw new DukeException("Please enter the description and deadline of your task");
            }
            taskParams = inputDetails.trim().split("/by ");
            if (taskParams.length != 2) {
                throw new DukeException("Please use the format: deadline (DESCRIPTION) /by " + "(YYYY-MM-DD)");
            }
            return new AddCommand(TaskType.DEADLINE, taskParams[0], taskParams[1]);
        case "event":
            if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
                throw new DukeException("Please enter the description of your event task");
            }
            taskParams = inputDetails.trim().split("/at ");
            if (taskParams.length != 2) {
                throw new DukeException("Please use the format: event (DESCRIPTION) /at " + "(YYYY-MM-DD)");
            }
            return new AddCommand(TaskType.EVENT, taskParams[0], taskParams[1]);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :(");
        }
    }
}