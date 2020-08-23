package duke.command;

import duke.storage.*;
import duke.ui.Ui;
import duke.task.*;
import duke.exception.DukeException;

import java.util.ArrayList;

/**
 * Encapsulates a Command which creates a new Deadline task to add to the task
 * list.
 */
public class DeadlineCommand extends Command {

    private String description;
    private String time;

    public DeadlineCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> lib = tasks.getTaskList();

        try {
            Deadline deadline = new Deadline(description, time);
            tasks.addDeadline(deadline);
            ui.printAddStatements(deadline.toString(), lib.size());
        } catch (DukeException ex) {
            ui.printExceptions(ex);
        }

    }

    @Override
    public boolean isDone() {
        return false;
    }

}
