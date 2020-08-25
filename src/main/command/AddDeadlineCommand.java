package main.command;

import java.time.LocalDateTime;

import main.task.Deadline;
import main.task.TaskList;
import main.ui.Ui;

/**
 * Represents the add deadline command.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.1
 * @since v0.1
 */
public class AddDeadlineCommand implements Command {
    private final Deadline deadline;

    /**
     * Constructs an AddDeadlineCommand instance and the Deadline object
     * with the description and the deadline of the task.
     * @param description the description of the task.
     * @param dateTime the deadline of the task.
     */
    public AddDeadlineCommand(String description, LocalDateTime dateTime) {
        deadline = new Deadline(description, dateTime);
    }

    /**
     * Adds the Deadline object into the task list and prints add success
     * from the ui.
     * @param ui the ui used to print out responses.
     * @param tasks the task list.
     */
    @Override
    public void execute(Ui ui, TaskList tasks) {
        tasks.add(deadline);
        ui.printAddSuccess(deadline, tasks.size());
    }

    /**
     * Returns true since there can still be commands after this.
     * @return true.
     */
    @Override
    public boolean hasCommand() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddDeadlineCommand) {
            AddDeadlineCommand o = (AddDeadlineCommand) obj;
            return this.deadline.equals(o.deadline);
        }
        return false;
    }
}
