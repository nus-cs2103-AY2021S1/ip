package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.Storage;
import duke.Ui;
import duke.exception.DukeIndexException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents an DoneCommand that is part of the Command class, regarding finishing a task.
 */

public class DoneCommand extends Command {
    private final int num;

    /**
     * Constructor for the Done Command
     * @param command the specific command instructions
     * @param num the task number
     */
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
                ui.doneMessage(current.getDone(), current.getDescription());
                storage.editFile(num - 1);
            } else {
                throw new DukeException(ui.invalidIndexMessage());
            }
        } catch (DukeException | IOException e) {
            ui.errorEncounter(e);
        }
    }

    /**
     * Executes the command to complete a task from the list of tasks on FXML.
     *
     * @param list Tasklist containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    @Override
    public String executeChat(TaskList list, Ui ui, Storage storage) {
        try {
            if (num <= list.size()) {
                Task current = list.get(num - 1);
                storage.editFile(num - 1);
                return ui.doneMessage(current.getDone(), current.getDescription(), true);
            } else {
                throw new DukeIndexException(ui.invalidIndexMessage());
            }
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
