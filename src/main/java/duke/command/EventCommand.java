package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

/**
 * Add a new event into the task list
 */
public class EventCommand extends Command {
    Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Insert an event into the task list and save it in the storage file
     *
     * @param taskList current task list
     * @param ui       text ui interface
     * @param storage  storage file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(event);
        storage.save(tasks);

        int size = tasks.size();
        ui.printAddConfirmation(tasks.show(size - 1), size);
    }
}
