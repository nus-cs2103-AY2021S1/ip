package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.task.Event;

public class EventCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Event event = ui.getEvent();
        taskList.addTask(event);
        ui.addTask(event, taskList);
        storage.addData(event.store());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
