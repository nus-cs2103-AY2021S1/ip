package duke.command;

import duke.task.Deadline;
import duke.util.Storage;
import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.Date;

/**
 * Responsible for executing a deadline command.
 */
public class DeadlineCommand extends Command {
    String description;
    Date by;

    public DeadlineCommand(String description, Date by) {
        super(true);
        this.description = description;
        this.by = by;
    }

    /**
     * Executes a deadline command.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        storage.save(tasks);
        String response = String.format(
                "I've added this task:\n  %s \nNow you have %s tasks in the list.",
                task, tasks.size()
        );
        ui.printResponse(response);
    }

    @Override
    public String toString() {
        return "deadline <task> /by <date>";
    }
}
