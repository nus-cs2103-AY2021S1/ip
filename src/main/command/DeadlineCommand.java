package main.command;

import java.time.LocalDateTime;
import java.util.HashSet;

import main.task.Deadline;
import main.task.TaskList;
import main.ui.Ui;

/**
 * Represents the add deadline command.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.3
 * @since v0.1
 */
public class DeadlineCommand implements Command {
    private final Deadline deadline;

    /**
     * Constructs an DeadlineCommand instance and the Deadline object
     * with the description and the deadline of the task.
     * @param description the description of the task.
     * @param dateTime the deadline of the task.
     * @param options the options of the task.
     * @param tags the tags associated with the task.
     */
    public DeadlineCommand(String description, LocalDateTime dateTime,
                           HashSet<Option> options, String[] tags) {
        deadline = new Deadline(description, dateTime, options, tags);
    }

    /**
     * Adds the Deadline object into the task list and prints add success
     * from the ui.
     * @param ui the ui used to print out responses.
     * @param tasks the task list.
     * @return the string indicating the task has been added successfully.
     */
    @Override
    public String execute(Ui ui, TaskList tasks) {
        tasks.add(deadline);

        return ui.printAddSuccess(deadline, tasks.size());
    }

    /**
     * Returns true since there can still be commands after this.
     * @return true.
     */
    @Override
    public boolean hasCommandAfter() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeadlineCommand) {
            DeadlineCommand o = (DeadlineCommand) obj;
            return this.deadline.equals(o.deadline);
        }
        return false;
    }
}
