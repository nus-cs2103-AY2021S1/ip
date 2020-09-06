package duke.command;

import java.time.LocalDate;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;


public class DateListCommand extends Command {
    private LocalDate localDate;

    /**
     * Initializes the command with the LocalDate to match with the tasks in TaskList.
     *
     * @param localDate
     */
    public DateListCommand(LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Searches for tasks that have the same date and time as localDate
     * and prints them in the Ui.
     *
     * @param taskList The TaskList used by Duke.
     * @param storage  The Storage used by Duke.
     * @return CommandResult object for ui
     * @throws DukeException
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) throws DukeException {
        assert taskList != null && storage != null;
        StringBuilder stringBuilder = new StringBuilder();
        int numberOfTasksFound = 0;
        stringBuilder.append("Here are the tasks with the date: " + localDate.toString() + "\n");

        for (int i = 0; i < taskList.numberOfTasks(); i++) {
            Task currentTask = taskList.getTask(i);
            if (currentTask instanceof Deadline) {
                Deadline deadline = (Deadline) currentTask;
                if (deadline.getLocalDate().equals(localDate)) {
                    stringBuilder.append((i + 1) + ". " + deadline.toString() + "\n");
                    numberOfTasksFound++;
                }
            } else if (currentTask instanceof Event) {
                Event event = (Event) currentTask;
                if (event.getLocalDate().equals(localDate)) {
                    stringBuilder.append((i + 1) + ". " + event.toString() + "\n");
                    numberOfTasksFound++;
                }
            }
        }
        if (numberOfTasksFound > 0) {
            return new CommandResult(stringBuilder.toString());
        } else {
            return new CommandResult("There are no tasks with the date: "
                    + localDate.toString());
        }
    }

}
