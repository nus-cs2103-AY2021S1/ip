package duke.commands;

import java.time.temporal.ChronoUnit;

import duke.TimeModification;
import duke.tasks.Task;
import duke.tasks.TaskDeadline;
import duke.tasks.TaskEvent;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class CommandReschedulePostpone extends CommandReschedule {

    public static final String COMMAND_STRING = "postpone";

    private int amount;
    private ChronoUnit timeUnit;

    /**
     * Constructor for {@code CommandReschedulePostpone}.
     *
     * @param taskList
     * @param ui
     * @param timeModification
     */
    public CommandReschedulePostpone(TaskList taskList, Ui ui, TimeModification timeModification) {
        super(taskList, ui, timeModification.getTaskIndex());
        this.amount = timeModification.getAmount();
        this.timeUnit = timeModification.getTimeUnit();
    }

    @Override
    public void execute() {
        Task task = taskList.get(taskIndex);
        if (task.toString().charAt(1) == 'D') {
            ((TaskDeadline) task).postpone(amount, timeUnit);
        } else if (task.toString().charAt(1) == 'E') {
            ((TaskEvent) task).postpone(amount, timeUnit);
        }
        String output = String.format("Pushing back by %d %s\n", amount, timeUnit)
                + "Okay, I've postponed it. Updated as follows: \n"
                + taskList.get(taskIndex);
        ui.outputBlockToUser(output);
    }
}
