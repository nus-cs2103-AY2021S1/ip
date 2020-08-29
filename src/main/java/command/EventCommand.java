package command;

import duke.Storage;
import duke.TaskList;
import task.Event;
import ui.Ui;

/**
 * Add a new event into the task list
 */
public class EventCommand extends Command {
    private Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Insert an event into the task list and save it in the storage file
     *
     * @param tasks   current task list
     * @param ui      text ui interface
     * @param storage storage file
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(event);
        storage.save(tasks);

        int size = tasks.size();
        return new CommandResult(ui.printAddConfirmation(tasks.show(size - 1), size));
    }
}
