import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command {

    private int taskNum;

    public DeleteCommand(String input) {
        String[] splitInput = input.split(" ");
        this.taskNum = Integer.parseInt(splitInput[1]);
    }

    public String handle(String input, TaskManager taskManager, Storage fileHandler) throws IOException {
        if (taskManager.getTasksList().isEmpty() || taskManager.getTasksList().size() < taskNum) {
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
