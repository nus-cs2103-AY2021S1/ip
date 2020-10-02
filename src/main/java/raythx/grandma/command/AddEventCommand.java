package raythx.grandma.command;

import raythx.grandma.exception.DukeException;
import raythx.grandma.exception.MissingDetailsException;
import raythx.grandma.exception.WrongDescriptionException;
import raythx.grandma.storage.Storage;
import raythx.grandma.task.Event;
import raythx.grandma.task.Task;
import raythx.grandma.task.TaskList;
import raythx.grandma.ui.Ui;

/**
 * Represents a Add Event Command.
 */
public class AddEventCommand extends AddCommand {
    public AddEventCommand(String taskDescriptionDeadline) {
        super(taskDescriptionDeadline);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] descriptionSplit = taskDescriptionDeadline.split(" /at ", 2);
        String[] hashtagSplit = descriptionSplit[0].split(" #", 2);
        Task task;
        if (hashtagSplit.length == 1) {
            throw new MissingDetailsException();
        } else if (descriptionSplit.length == 1) {
            task = new Event(hashtagSplit[0], hashtagSplit[1]);
        } else if (descriptionSplit.length == 2) {
            task = new Event(hashtagSplit[0], hashtagSplit[1], descriptionSplit[1]);
        } else {
            throw new WrongDescriptionException();
        }
        tasks.addTask(task);
        ui.appendMessage("Got it, here yur task bij\n    " + task.toString()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }
}
