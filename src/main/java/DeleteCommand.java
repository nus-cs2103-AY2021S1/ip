import java.io.IOException;

public class DeleteCommand extends IOHandler {

    private int taskNum;

    public DeleteCommand(String input) {
        String[] splitInput = input.split(" ");
        this.taskNum = Integer.parseInt(splitInput[1]);
    }

    public String handleIO(String input, TaskManager taskManager, FileHandler fileHandler) throws IOException {
        Task deletedTask = taskManager.getTask(taskNum - 1);
        taskManager.removeTask(taskNum);
        fileHandler.writeToFile(taskManager);

        return "Noted. I've removed this task:\n"
                + deletedTask + "\nNow you have " + taskManager.getNumTasks()
                + " tasks in the list";
    }
}
