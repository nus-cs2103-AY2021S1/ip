package duke.commands;

import java.time.temporal.ChronoUnit;

import duke.TimeModification;
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
        /*Task task = taskList.get(taskIndex);
        if (task.toString().charAt(1) == 'D') {
            TaskDeadline newTask = (TaskDeadline) task;
            newTask.postpone(amount, timeUnit);
            taskList.set(taskIndex, newTask);
        } else if (task.toString().charAt(1) == 'E') {
            TaskEvent newTask = (TaskEvent) task;
            newTask.postpone(amount, timeUnit);
            taskList.set(taskIndex, newTask);
        }*/
        taskList.get(taskIndex).postpone(amount, timeUnit);
        String output = "Adding " + amount + timeUnit + "\n"
                + "Okay, I've postponed it. Updated as follows: \n"
                + taskList.get(taskIndex);
        ui.outputBlockToUser(output);
    }
}
