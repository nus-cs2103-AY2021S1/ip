package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

public class EventCommand extends Command {
    Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(event);
        storage.save(tasks);

        int size = tasks.size();
        ui.printAddConfirmation(tasks.show(size - 1), size);
    }
}
