import java.io.IOException;

/**
 * Represents a class that handles the priority level of a task.
 */
public class PriorityCommand extends Command {

    private int taskNum;
    private int priority;

    public PriorityCommand() {}

    public PriorityCommand(String input) {
        String[] splitInput = input.split(" ");
        this.taskNum = Integer.parseInt(splitInput[1]);
        this.priority = Integer.parseInt(splitInput[2]);
    }

    /**
     * Lists out the different priority levels and their corresponding numbers.
     * @param priority priority level.
     * @return String of a list of the different priority levels.
     */
    public String getPriority(int priority) {
        if (this.priority == 1) {
            return "HIGH";
        } else if (this.priority == 2) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
    }

    /**
     * Changes the priority level of the the task.
     * @param input user input.
     * @param taskManager task manager that contains a list of tasks.
     * @param fileHandler saves input into a file.
     * @return String of the task and along with its new priority level.
     * @throws IOException
     */
    public String handle(String input, TaskManager taskManager, Storage fileHandler) throws IOException {
        if (input.equals("priority")) {
            return "Priority levels:\n HIGH - 1\n MEDIUM - 2\n LOW - 3";
        } else if (taskManager.getTaskList().isEmpty() || taskManager.getTaskList().size() < taskNum) {
            return "This task doesn't exist!";
        } else if (this.priority > 3 || this.priority < 1) {
            return "This priority level doesn't exist!";
        } else {
            taskManager.setTaskPriority(taskNum, priority);
            fileHandler.writeToFile(taskManager);

            return "Noted. I've changed the priority of this task to: " + this.getPriority(this.priority) + "\n"
                    + taskManager.getTask(taskNum - 1);
        }
    }
}
