package duke.command;

import java.util.Date;

import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Responsible for executing an event command.
 */
public class EventCommand extends Command {
    private String description;
    private Date at;

    /**
     * Constructs an EventCommand.
     *
     * @param description The description of the event.
     * @param at The date of the event.
     */
    public EventCommand(String description, Date at) {
        super(true);
        this.description = description;
        this.at = at;
    }

    /**
     * Executes an event command and returns a response.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     * @return Message when the command is completed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(description, at);
        int previousTaskSize = tasks.size();
        tasks.add(task);
        int subsequentTaskSize = tasks.size();
        assert (previousTaskSize + 1 == subsequentTaskSize);
        storage.save(tasks);
        return String.format(
                "I've added this task:\n%s\nNow you have %s tasks in the list.",
                task, tasks.size()
        );
    }

    @Override
    public String toString() {
        return "event <description> /at <date>";
    }
}
