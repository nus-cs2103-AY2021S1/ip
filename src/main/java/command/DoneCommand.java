package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.List;

public class DoneCommand extends Command {
    public DoneCommand(String command, int order) {
        super(command, order);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> taskList = tasks.getTasks();
        String message = "";
        assert order >= 1 : "order should be at least one";
        Task task = taskList.get(order - 1);
        task.markAsDone();
        message = message + "  Nice! I've marked this task as done:\n" + "    ["
                + task.getStatusIcon() + "] "
                + task.getDescription();

        try {
            storage.writeToFile(taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        return message;
    }
}
