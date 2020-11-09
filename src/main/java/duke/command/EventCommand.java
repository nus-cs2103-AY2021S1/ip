package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

/**
 * Represents a command which creates a task with an event.
 */
public class EventCommand extends Command {

    private String[] commandDetails;

    /**
     * Creates a new instance of an EventCommand.
     *
     * @param commandDetails String array with task details.
     */
    public EventCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes the operation for an task with an event to be added.
     *
     * @param tasks TaskList linked to the program.
     * @param ui Ui linked to the program.
     * @param storage Storage linked to the program.
     * @return String that contains the executed EventCommand.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String at = commandDetails[1].split(" ", 2)[1];
        Event event = new Event(commandDetails[0], at);
        tasks.getTasks().add(event);
        return ui.showTask(event, tasks.getTasks().size());
    }

    /**
     * Returns a boolean that dictates if the program is running.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
