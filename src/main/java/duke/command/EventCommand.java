package duke.command;

import duke.task.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EventCommand extends Command {
    Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(event);
        storage.save(taskList);

        int size = taskList.size();
        ui.printAddConfirmation(taskList.show(size - 1), size);
    }
}
