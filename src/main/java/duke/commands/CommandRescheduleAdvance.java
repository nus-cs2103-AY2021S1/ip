package duke.commands;

import java.time.temporal.ChronoUnit;

import duke.TimeModification;
import duke.tasks.Task;
import duke.tasks.TaskDeadline;
import duke.tasks.TaskEvent;
import duke.tasks.TaskList;

public class CommandRescheduleAdvance extends CommandReschedule {

    public static final String COMMAND_STRING = "advance";

    private int amount;
    private ChronoUnit timeUnit;

    /**
     * Constructor for {@code CommandRescheduleAdvance}.
     *
     * @param taskList
     * @param timeModification
     */
    public CommandRescheduleAdvance(TaskList taskList, TimeModification timeModification) {
        super(taskList, timeModification.getTaskIndex());
        this.amount = timeModification.getAmount();
        this.timeUnit = timeModification.getTimeUnit();
    }

    @Override
    public String execute() {
        Task task = taskList.get(taskIndex);
        if (task instanceof TaskDeadline) {
            TaskDeadline newTask = (TaskDeadline) task;
            newTask.advance(amount, timeUnit);
        } else if (task instanceof TaskEvent) {
            TaskEvent newTask = (TaskEvent) task;
            newTask.advance(amount, timeUnit);
        }
        String output = String.format("Advancing by %d %s\n", amount, timeUnit)
                + "Okay, I've brought it forward. Updated as follows: \n"
                + taskList.get(taskIndex);
        return output;
    }
}
