package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.exception.DukeException;
import duke.storage.StateManager;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

public class DateAndTimeListCommand extends Command {
    private LocalDate localDate;
    private LocalTime localTime;

    /**
     * Initializes the command with the LocalDate and LocalTime to find in TaskList.
     *
     * @param localDate The LocalDate to match with tasks in the TaskLise.
     * @param localTime The LocalTime to match with tasks in the TaskList.
     */
    public DateAndTimeListCommand(LocalDate localDate, LocalTime localTime) {
        this.localDate = localDate;
        this.localTime = localTime;
    }



    /**
     * Searches for tasks that have the same date and time as localDate and localTime
     * and prints them in the Ui.
     *
     * @param taskList The TaskList used by Duke.
     * @param stateManager  The Storage used by Duke.
     * @throws DukeException
     */
    @Override
    public CommandResult execute(TaskList taskList, StateManager stateManager) throws DukeException {
        assert taskList != null && stateManager != null;
        int numberOfTasksFound = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks with the date: "
                + localDate.toString() + " and time: " + localTime.toString() + "\n");

        for (int i = 0; i < taskList.numberOfTasks(); i++) {
            Task currentTask = taskList.getTask(i);
            if (currentTask instanceof Deadline) {
                Deadline deadline = (Deadline) currentTask;
                if (deadline.getLocalDate().equals(localDate)
                        && deadline.getLocalTime().equals(localTime)) {
                    stringBuilder.append((i + 1) + ". " + deadline.toString() + "\n");
                    numberOfTasksFound++;
                }
            } else if (currentTask instanceof Event) {
                Event event = (Event) currentTask;
                if (event.getLocalDate().equals(localDate)
                        && event.getLocalTime().equals(localTime)) {
                    stringBuilder.append((i + 1) + ". " + event.toString() + "\n");
                    numberOfTasksFound++;
                }
            }
        }

        if (numberOfTasksFound > 0) {
            return new CommandResult(stringBuilder.toString());
        } else {
            return new CommandResult("There are no tasks with the date: "
                    + localDate.toString()
                    + " and time: " + localTime.toString());
        }
    }

}
