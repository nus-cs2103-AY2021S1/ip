package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidTaskDateTimeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command{
    private final String description;
    private final String by;

    public AddDeadlineCommand(String description, String by) {
        super(false);
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = new Deadline(description, by);
            tasks.addTask(task);
            storage.save(tasks);
            ui.printMessage("Got it. I've added this deadline: \n\t   "
                    + task + "\n\t "
                    + "Now you have "
                    + getTaskDescription(tasks.getNumberOfTask())
                    + " in the list.");
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateTimeException();
        }
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
