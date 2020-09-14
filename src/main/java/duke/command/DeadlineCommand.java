package duke.command;

import java.time.LocalDate;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Implements deadline command objects.
 *
 * @author Audrey Felicio Anwar
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDate time;

    /**
     * Initializes a DeadlineCommand object.
     * @param description The task description.
     * @param time The task deadline time.
     */
    public DeadlineCommand(String description, LocalDate time) {
        this.description = description;
        this.time = time;
    }

    /**
     * Executes the given command.
     *
     * @param tasks Task list the user currently have.
     * @param ui Tool to interact with user.
     * @param storage Storage to load and save data.
     * @return Responses to be passed to user.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Deadline(description, false, time);
        tasks.addTask(newTask, ui);
        storage.saveTasks(tasks.getTasks());
        return ui.getResponses();
    }
}
