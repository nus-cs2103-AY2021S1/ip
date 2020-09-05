package duke.command;

import duke.DukeStateManager;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

/**
 * Represents a command to undo the previous command that resulted in changes. Created by using "undo".
 */
public class UndoCommand extends Command {

    /**
     *
     * @param tasks TaskList containing all tasks
     * @param ui Ui for formatting of message Strings to be displayed to user
     * @param storage Storage to retrieve and store Tasks entered by user
     * @param dukeStateManager DukeStateManager to manage the current state of Duke
     * @return Response object containing the formatted feedback String to be displayed by the GUI
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage, DukeStateManager dukeStateManager) {
        dukeStateManager.undo();
        return new Response(false, "Undo previous command!");
    }
}
