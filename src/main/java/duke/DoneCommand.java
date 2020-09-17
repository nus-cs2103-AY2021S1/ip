package duke;

public class DoneCommand extends Command {
    public DoneCommand(TaskList taskList, String input) {
        super(Command.DONE, taskList, input);
    }

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
            String userMessage = Ui.informTaskDone(selectedTask) + Ui.informNumberOfTasksRemaining(taskList);
            return userMessage;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid index!");
        }
    }

    private boolean isValidFormat (String[]inputArr){
        try {
            int checkIfArgumentIsInteger = Integer.parseInt(inputArr[1]);
            return inputArr.length == 2;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
