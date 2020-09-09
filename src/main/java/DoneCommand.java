import java.io.IOException;

public class DoneCommand extends Command {

    private int taskNum;

    public DoneCommand(String input) {
        assert input != null;

        String[] splitInput = input.split(" ");
        this.taskNum = Integer.parseInt(splitInput[1]);
    }

    @Override
    public String handle(String input, TaskManager taskManager, Storage fileHandler) throws IOException {
        if (taskManager.getTasksList().isEmpty() || taskManager.getTasksList().size() < taskNum) {
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
