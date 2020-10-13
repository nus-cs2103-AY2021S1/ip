package duke;

public class DoneCommand extends Command {
    public DoneCommand(TaskList taskList, String input) {
        super(Command.DONE, taskList, input);
    }

    /**
     * Marks the task specified by user as done.
     *
     * @return Message informing user that the task is marked as done.
     * @throws DukeException If user did not provide index of the task to be marked as done.
     */
    public String execute() throws DukeException {
        try {
            String[] parsedInput = userInput.split(Parser.SPACE);
            if (isValidFormat(parsedInput)) {
                int taskIndex = Integer.parseInt(parsedInput[1]) - 1;
                return markTaskAsDone(taskIndex);
            } else {
                throw new DukeException("You forgot to include the task index..");
            }
        } catch (DukeException e) {
            throw e;
        }

    }

    private String markTaskAsDone(int taskIndex) throws DukeException {
        try {
            Task selectedTask = taskList.get(taskIndex);
            selectedTask.markAsDone();
            String userMessage = Ui.informTaskDone(selectedTask);
            return userMessage;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid index!");
        }
    }

    /**
     * Checks if the user input is in a valid format supported by Duke.
     *
     * @param inputArr The array containing parsed user input.
     * @return True if the user input is in a valid format.
     */
    private boolean isValidFormat(String[] inputArr) {
        try {
            int checkIfArgumentIsInteger = Integer.parseInt(inputArr[1]);
            return inputArr.length == 2;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
