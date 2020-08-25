package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.task.Event;

/**
 * The EventCommand class contains methods pertaining to the EventCommand.
 *
 *  @author  Yen Pin Hsuan
 *  @version 1.0
 */
public class EventCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            Event event = ui.getEvent();
            taskList.addTask(event);
            ui.addTask(event, taskList);
            storage.addData(event.store());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
