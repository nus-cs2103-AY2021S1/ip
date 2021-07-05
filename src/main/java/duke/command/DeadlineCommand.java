package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Deadline;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Encapsulates a Command which creates a new Deadline task to add to the task
 * list.
 */
public class DeadlineCommand extends Command {

    private String description;
    private String time;

    /**
     * Creates a deadline command.
     * @param description The task details.
     * @param time The date of the deadline.
     */
    public DeadlineCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> lib = tasks.getTaskList();

        try {
            Deadline deadline = new Deadline(description, time);
            tasks.addDeadline(deadline);
            return ui.printAddStatements(deadline.toString(), lib.size());
        } catch (DukeException ex) {
            return ui.printExceptions(ex);
        }

    }

}
