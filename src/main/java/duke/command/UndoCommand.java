package duke.command;

import java.io.IOException;

import duke.exceptions.LatestChangeException;
import duke.storage.DukeStateManager;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.ErrorResponse;
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
        try {
            dukeStateManager.undo();
            return new Response(false, "Undo previous command!");
        } catch (IOException e) {
            return new ErrorResponse(false, "undo failed, changes not saved");
        } catch (LatestChangeException e) {
            return new ErrorResponse(false, e.getFriendlyMessage());
        }
    }
}
