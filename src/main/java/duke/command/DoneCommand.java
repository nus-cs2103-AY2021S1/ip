package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import java.io.IOException;

/**
 * Represents an DoneCommand that is part of the Command class, regarding finishing a task.
 */

public class DoneCommand extends Command {
    private final int num;

    public DoneCommand(String command, int num) {
        super(command, false);
        this.num = num;
    }

    /**
     * Executes the command to complete a task from the list of tasks.
     *
     * @param list Tasklist containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            if (num <= list.size()) {
                Task current = list.get(num - 1);
                current.markAsDone();
                ui.doneMessage(current.isDone, current.description);
                storage.editFile(num - 1);
            } else {
                throw new DukeException("☹ OOPS!!! there is no such task");
            }
        } catch (DukeException | IOException e) {
            ui.errorEncounter(e);
        }
    }
}
