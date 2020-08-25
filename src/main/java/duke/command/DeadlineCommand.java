package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDate time;

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
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Deadline(this.description, false, this.time);
        tasks.addTask(newTask);
    }
}
