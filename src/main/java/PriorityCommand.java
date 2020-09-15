import java.io.IOException;

public class PriorityCommand extends Command {

    private int taskNum;
    private int priority;

    public PriorityCommand() {}

    public PriorityCommand(String input) {
        String[] splitInput = input.split(" ");
        this.taskNum = Integer.parseInt(splitInput[1]);
        this.priority = Integer.parseInt(splitInput[2]);
    }

    public String getPriority(int priority) {
        if (this.priority == 1) {
            return "HIGH";
        } else if (this.priority == 2) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
    }

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
