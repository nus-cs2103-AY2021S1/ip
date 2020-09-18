package clippy.command;

import clippy.exception.InvalidDateFormatException;
import clippy.storage.Storage;
import clippy.task.Event;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Represents a command that adds an event task when executed.
 */
public class AddEventCommand extends AddCommand {
    private String at;

    /**
     * Constructs a command object that adds an event task when executed.
     * 
     * @param taskDescription Literal description of the event task.
     * @param at Date/time period of event as a String, no pre-defined format.
     */
    public AddEventCommand(String taskDescription, String at) {
        super(taskDescription);
        this.at = at;
    }

    /**
     * Returns resulting message to be displayed by GUI after adding an event task.
     * Executes the command.
     * Save changes onto save file.
     *
     * @param tasks TaskList object used in the current Clippy session.
     * @param ui Ui object used in the current Clippy session.
     * @param storage Storage object used in the current Clippy session.
     * @return Resulting message to be displayed by GUI after adding an event task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Event(taskDescription, at);
        tasks.add(newTask);
        String output = ui.showAdded(newTask, tasks.size());

        tasks.updateAllTaskIndices();
        storage.save(tasks);
        
        return output;
    }
}
