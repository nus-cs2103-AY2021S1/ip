package duke;

public class DeleteCommand extends Command {
    public DeleteCommand(TaskList taskList, String input) {
        super(Command.DELETE, taskList, input);
    }

    public String execute() throws DukeException {
        String[] parsedInput = userInput.split(Parser.SPACE);
        try {
            if (isValidFormat(parsedInput)) {
                int taskIndex = Integer.parseInt(parsedInput[1]) - 1;
                return deleteTaskFromTaskList(taskIndex);
            } else {
                throw new DukeException("How to delete?! You forgot to include the task index.");
            }
        } catch (DukeException e) {
            throw e;
        }
    }

    private String deleteTaskFromTaskList(int taskIndex) throws DukeException {
        try {
            Task selectedTask = taskList.get(taskIndex);
            taskList.remove(taskIndex);
            String userMessage = Ui.informTaskDeleted(selectedTask) + Ui.informNumberOfTasksRemaining(taskList);
            return userMessage;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid index!");
        }
    }

    private boolean isValidFormat(String[] inputArr) {
        try {
            int checkIfArgumentIsInteger = Integer.parseInt(inputArr[1]);
            return inputArr.length == 2;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}