package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddTodoCommand extends Command {
    private final String description;
    public AddTodoCommand(String description) {
        super(false);
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.save(tasks);
        ui.printMessage("Got it. I've added this todo: \n\t   "
                + task + "\n\t "
                + "Now you have "
                + getTaskDescription(tasks.getNumberOfTask())
                + " in the list.");
    }

    public static String getTaskDescription(int noOfTask) {
        String taskDescription = "";
        if (noOfTask > 1) {
            taskDescription = noOfTask + " tasks";
        } else {
            taskDescription = noOfTask + " task";
        }
        return taskDescription;
    }

    @Override
    public boolean getIsExit() {
        return isExit;
    }
}
