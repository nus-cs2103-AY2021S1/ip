package duke.commands;

import java.time.temporal.ChronoUnit;

import duke.TimeModification;
import duke.tasks.Task;
import duke.tasks.TaskDeadline;
import duke.tasks.TaskEvent;
import duke.tasks.TaskList;

public class CommandReschedulePostpone extends CommandReschedule {

    public static final String COMMAND_STRING = "postpone";

    private int amount;
    private ChronoUnit timeUnit;

    /**
     * Constructor for {@code CommandReschedulePostpone}.
     *
     * @param taskList
     * @param timeModification
     */
    public CommandReschedulePostpone(TaskList taskList, TimeModification timeModification) {
        super(taskList, timeModification.getTaskIndex());
        this.amount = timeModification.getAmount();
        this.timeUnit = timeModification.getTimeUnit();
    }

    @Override
    public String execute() {
        Task task = taskList.get(taskIndex);
        if (task.toString().charAt(1) == 'D') {
            TaskDeadline newTask = (TaskDeadline) task;
            newTask.postpone(amount, timeUnit);
        } else if (task.toString().charAt(1) == 'E') {
            TaskEvent newTask = (TaskEvent) task;
            newTask.postpone(amount, timeUnit);
        }
        String output = String.format("Pushing back by %d %s\n", amount, timeUnit)
                + "Okay, I've postponed it. Updated as follows: \n"
                + taskList.get(taskIndex);
        return output;
    }
}
