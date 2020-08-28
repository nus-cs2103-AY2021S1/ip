package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

/**
 * Implements event command objects.
 *
 * @author Audrey Felicio Anwar
 */
public class EventCommand extends Command {
    private String description;
    private LocalDate time;

    public EventCommand(String description, LocalDate time) {
        this.description = description;
        this.time = time;
    }

    /**
     * Executes the given command.
     * 
     * @param tasks Task list the user currently have.
     * @param ui Tool to interact with user.
     * @param storage Storage to load and save data.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Event(description, false, time);
        tasks.addTask(newTask);
    }
}
