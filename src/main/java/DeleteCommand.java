import java.io.IOException;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    private int taskNum;

    public DeleteCommand(String input) {
        assert input != null;

        String[] splitInput = input.split(" ");
        this.taskNum = Integer.parseInt(splitInput[1]);
    }

    /**
     * Deletes a task from the task list.
     * @param input input entered by user.
     * @param taskManager task manager that contains a list of tasks.
     * @param fileHandler saves the input into a file.
     * @return String message of the task that was deleted.
     * @throws IOException
     */
    public String handle(String input, TaskManager taskManager, Storage fileHandler) throws IOException {
        if (taskManager.getTaskList().isEmpty() || taskManager.getTaskList().size() < taskNum) {
            return "There's nothing to delete!";

        } else {

            Task deletedTask = taskManager.getTask(taskNum - 1);
            taskManager.removeTask(taskNum);
            fileHandler.writeToFile(taskManager);

            return "Noted. I've removed this task:\n"
                    + deletedTask + "\nNow you have " + taskManager.getNumTasks()
                    + " tasks in the list";
        }
    }
}
