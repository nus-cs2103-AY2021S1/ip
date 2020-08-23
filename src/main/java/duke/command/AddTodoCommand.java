package duke.command;

import duke.storage.*;
import duke.task.*;
import duke.tasklist.*;
import duke.ui.*;

public class AddTodoCommand extends Command {
    private final String typeOfTask;
    private final String description;
    public AddTodoCommand(String typeOfTask, String description) {
        super(false);
        this.typeOfTask = typeOfTask;
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.save(tasks);
        ui.printMessage("Got it. I've added this todo: \n\t   " +
                task + "\n\t " +
                "Now you have " +
                getTaskDescription(tasks.getNumberOfTask()) +
                " in the list.");
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
