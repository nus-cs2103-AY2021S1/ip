package duke.command;

import duke.storage.*;
import duke.tasklist.*;
import duke.ui.*;

public class DeleteCommand extends Command{
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int noOfTask = tasks.getNumberOfTask() - 1;
        ui.printMessage("Noted. I've removed this task:\n\t   " +
                tasks.getTask(taskNumber) + "\n\t " +
                "Now you have " +
                getTaskDescription(noOfTask) +
                " in the list.");
        tasks.removeTask(taskNumber);
        storage.save(tasks);
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
