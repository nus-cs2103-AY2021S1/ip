package duke.command;

import java.util.Date;

import duke.task.Deadline;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Responsible for executing a deadline command.
 */
public class DeadlineCommand extends Command {
    private String description;
    private Date by;

    /**
     * Constructs a DeadlineCommand.
     *
     * @param description The description of the deadline.
     * @param by The date of the deadline.
     */
    public DeadlineCommand(String description, Date by) {
        super(true);
        this.description = description;
        this.by = by;
    }

    /**
     * Executes a DeadlineCommand.
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

    /**
     * Returns a response after executing the deadline command.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     * @return Message when command is completed.
     */
    @Override
    public String executeWithResponse(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        storage.save(tasks);
        return String.format(
                "I've added this task:\n  %s \nNow you have %s tasks in the list.",
                task, tasks.size()
        );
    }

    @Override
    public String toString() {
        return "deadline <task> /by <date>";
    }
}
