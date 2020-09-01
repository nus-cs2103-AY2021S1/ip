package raythx98.duke.command;

import raythx98.duke.exception.DukeException;
import raythx98.duke.storage.Storage;
import raythx98.duke.task.Deadline;
import raythx98.duke.task.Task;
import raythx98.duke.task.TaskList;
import raythx98.duke.ui.Ui;

public class AddDeadlineCommand extends AddCommand {
    public AddDeadlineCommand(String taskDescriptionDeadline) {
        super(taskDescriptionDeadline);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] descriptionSplit = taskDescriptionDeadline.split(" /by ", 2);
        Task task;
        if (descriptionSplit.length == 1) {
            task = new Deadline(descriptionSplit[0]);
        } else if (descriptionSplit.length == 2) {
            task = new Deadline(descriptionSplit[0], descriptionSplit[1]);
        } else {
            throw new DukeException("Invalid Description");
        }
        tasks.addTask(task);
        ui.showOnScreen("Got it, here yur task bij\n" + task.toString()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }
}
