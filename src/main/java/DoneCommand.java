import java.io.IOException;

/**
 * Changes the completion status of a task to done.
 */
public class DoneCommand extends Command {

    private int taskNum;

    public DoneCommand(String input) {
        assert input != null;

        String[] splitInput = input.split(" ");
        this.taskNum = Integer.parseInt(splitInput[1]);
    }

    /**
     * Changes the completion status of a task to done.
     * @param input input entered by user.
     * @param taskManager task manager that contains a list of tasks.
     * @param fileHandler saves the input into a file.
     * @return String message with the new completion status of the task.
     * @throws IOException
     */
    @Override
    public String handle(String input, TaskManager taskManager, Storage fileHandler) throws IOException {
        if (taskManager.getTaskList().isEmpty() || taskManager.getTaskList().size() < taskNum) {
            return "This task doesn't exist!";

        } else if (taskManager.getTask(this.taskNum - 1).isDone) {
            return "This task is already done!";

        } else {
            taskManager.setTaskDone(this.taskNum);
            fileHandler.writeToFile(taskManager);
            return "Nice! I've marked this task as done:\n"
                    + taskManager.getTask(taskNum - 1);
        }
    }
}
