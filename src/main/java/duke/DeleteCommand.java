package duke;

public class DeleteCommand extends Command {
    public DeleteCommand(TaskList taskList, String input) {
        super(Command.DELETE, taskList, input);
    }

    /**
     * Deletes the task specified by user from the TaskList.
     *
     * @return Description of the task deleted.
     * @throws DukeException If there are missing information in the user input.
     */
    public String execute() throws DukeException {
        String[] parsedInput = userInput.split(Parser.SPACE);
        if (isValidSingleArgumentFormat(parsedInput)) {
            return deleteTask(parsedInput[1]);
        } else if (isValidMultipleArgumentsFormat(parsedInput)) {
            return deleteMultipleTasks(parsedInput[1]);
        } else {
            throw new DukeException("You forgot to include the task index or your task index is out of range.");
        }
    }

    /**
     * Deletes a task
     *
     * @param s Contains the index of the task to be deleted in String.
     * @return Description of the task deleted.
     */
    private String deleteTask(String s) {
        int taskIndex = Integer.parseInt(s) - 1;
        return deleteTaskFromTaskList(taskIndex);
    }

    private String deleteTaskFromTaskList(int taskIndex) {
        Task selectedTask = taskList.get(taskIndex);
        taskList.remove(taskIndex);
        String userMessage = Ui.informTaskDeleted(selectedTask) + Ui.informNumberOfTasksRemaining(taskList);
        return userMessage;
    }

    /**
     * Deletes more than one tasks.
     *
     * @param taskIndexArguments Contains the indexes of the tasks to be deleted in String.
     * @return Description of the tasks deleted.
     */
    private String deleteMultipleTasks(String taskIndexArguments) {
        String[] taskIndexes = taskIndexArguments.split(Parser.COMMA);
        Task[] tasksToDelete = new Task[taskIndexes.length];
        return deleteTasksFromTaskList(taskIndexes, tasksToDelete);
    }

    private String deleteTasksFromTaskList(String[] taskIndexes, Task[] tasksToDelete) {
        for (int i = 0; i < taskIndexes.length; i++) {
            int taskIndex = Integer.parseInt(taskIndexes[i]) - 1;
            Task selectedTask = taskList.get(taskIndex);
            tasksToDelete[i] = selectedTask;
        }
        for (int i = 0; i < tasksToDelete.length; i++) {
            taskList.remove(tasksToDelete[i]);
        }
        String userMessage = Ui.informTasksDeleted(tasksToDelete) + Ui.informNumberOfTasksRemaining(taskList);
        return userMessage;
    }

    /**
     * Checks if the user input is in a valid format supported by Duke when the user is deleting one task.
     *
     * @param inputArr Refers to the parsed user input.
     * @return True if the user input is in a valid format.
     */
    private boolean isValidSingleArgumentFormat(String[] inputArr) {
        try {
            int taskIndex = Integer.parseInt(inputArr[1]);
            if (taskIndex > taskList.size()) {
                return false;
            }
            return inputArr.length == 2;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the user input is in a valid format supported by Duke when the user is deleting more than one task.
     *
     * @param inputArr Refers to the parsed user input.
     * @return True if the user input is in a valid format.
     */
    private boolean isValidMultipleArgumentsFormat(String[] inputArr) {
        String deleteArgument = inputArr[1];
        String[] deleteTaskIndexes = deleteArgument.split(Parser.COMMA);
        if (deleteTaskIndexes.length == 0) {
            return false;
        }
        try {
            boolean indexesWithinRange = true;
            for (int i = 0; i < deleteTaskIndexes.length; i++) {
                int taskIndex = Integer.parseInt(deleteTaskIndexes[i].trim());
                if (taskIndex > taskList.size()) {
                    indexesWithinRange = false;
                }
            }
            return indexesWithinRange;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}