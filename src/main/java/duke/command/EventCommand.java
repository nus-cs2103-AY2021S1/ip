package duke.command;

import duke.task.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(event);
        storage.save(taskList);

        int size = taskList.size();
        ui.printAddConfirmation(taskList.show(size - 1), size);
    }
}
