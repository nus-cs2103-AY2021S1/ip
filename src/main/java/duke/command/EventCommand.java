package duke.command;

import duke.task.Event;
import duke.util.Storage;
import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.Date;

/**
 * Responsible for executing an event command.
 */
public class EventCommand extends Command {
    private String description;
    private Date at;

    public EventCommand(String description, Date at) {
        super(true);
        this.description = description;
        this.at = at;
    }

    /**
     * Executes an event command.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(description, at);
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
        return "event <task> /at <date>";
    }
}
