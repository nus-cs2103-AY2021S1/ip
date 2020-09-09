package raythx98.grandma.command;

import raythx98.grandma.exception.DukeException;
import raythx98.grandma.exception.WrongDescriptionException;
import raythx98.grandma.storage.Storage;
import raythx98.grandma.task.Task;
import raythx98.grandma.task.TaskList;
import raythx98.grandma.task.ToDo;
import raythx98.grandma.ui.Ui;

/**
 * Represents a Add To do Command.
 */
public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(String taskDescriptionDeadline) {
        super(taskDescriptionDeadline);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] descriptionSplit = taskDescriptionDeadline.split(" /by ", 2);
        Task task;
        if (descriptionSplit.length == 1) {
            task = new ToDo(descriptionSplit[0]);
        } else if (descriptionSplit.length == 2) {
            task = new ToDo(descriptionSplit[0], descriptionSplit[1]);
        } else {
            throw new WrongDescriptionException();
        }
        tasks.addTask(task);
        ui.appendMessage("Got it, here yur task bij\n    " + task.toString()
                + "\nNow you have " + tasks.getSize() + "tasks in the list.");
    }
}
