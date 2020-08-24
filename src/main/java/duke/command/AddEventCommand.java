package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidTaskDateTimeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private final String description;
    private final String at;

    public AddEventCommand(String description, String at) {
        super(false);
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = new Event(description, at);
            tasks.addTask(task);
            storage.save(tasks);
            ui.printMessage("Got it. I've added this event: \n\t   "
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
