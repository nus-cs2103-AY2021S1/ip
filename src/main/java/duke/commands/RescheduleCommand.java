package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command for Mrs Dino to reschedule tasks.
 */
public class RescheduleCommand extends Command {

    /**
     * Whether this command is a terminal command.
     */
    private final boolean HAS_FINISHED = false;

    /**
     * Updated date and tome of the task.
     */
    private String newDateAndTime;

    /**
     * Index in the todoList of the target task to reschedule.
     */
    private int indexOfTaskToReschedule;

    /**
     * Constructs a new RescheduleCommand.
     *
     * @param indexOfTaskToReschedule Index in the todoList of the target task to reschedule.
     * @param newDateAndTime Updated date and tome of the task.
     */
    public RescheduleCommand(int indexOfTaskToReschedule, String newDateAndTime) {
        this.newDateAndTime = newDateAndTime;
        this.indexOfTaskToReschedule = indexOfTaskToReschedule;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task targetTask = taskList.get(indexOfTaskToReschedule - 1);
        String isCompleted = targetTask.isCompleted() ? "1" : "0";
        String taskName = targetTask.getTaskName();
        Task rescheduledTask;
        switch (targetTask.getType()) {
        case("E"): {
            rescheduledTask = new Event(isCompleted, taskName, newDateAndTime);
            taskList.getList().set(indexOfTaskToReschedule - 1, rescheduledTask);
            storage.saveTasks(taskList);
            break;
        }
        case("D"): {
            rescheduledTask = new Deadline(isCompleted, taskName, newDateAndTime);
            taskList.getList().set(indexOfTaskToReschedule - 1, rescheduledTask);
            storage.saveTasks(taskList);
            break;
        }
        default: {
            throw new DukeException("Task of this type cannot be rescheduled!");
        }
        }
        ui.printTaskRescheduled(targetTask.toString(), rescheduledTask.toString());
        String messageAfterExecution = rescheduleTaskToString(targetTask, rescheduledTask);
        return new CommandResult(messageAfterExecution);
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}
