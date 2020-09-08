import java.io.IOException;

public class DoneCommand extends Command {

    private int taskNum;

    public DoneCommand(String input) {
        String[] splitInput = input.split(" ");
        this.taskNum = Integer.parseInt(splitInput[1]);
    }

    @Override
    public String handle(String input, TaskManager taskManager, Storage fileHandler) throws IOException {
        taskManager.setTaskDone(this.taskNum);
        fileHandler.writeToFile(taskManager);
        return "Nice! I've marked this task as done:\n"
                + taskManager.getTask(taskNum - 1);
    }
}
