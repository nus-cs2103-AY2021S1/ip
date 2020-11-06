package commands;

import java.time.LocalDateTime;

import duke.Storage;
import duke.Ui;
import exceptions.InvalidUpcomingInputException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.TimedTask;

/**
 * Represents an instruction from the user to print all tasks occurring within an input number of days.
 */

public class UpcomingCommand extends Command {

    private int dayRange;

    /**
     * Creates a RemindCommand object and checks whether the number of days input is valid.
     * @param dayRange number of days the user wants to search up to.
     * @throws InvalidUpcomingInputException if the number of days entered is invalid.
     */
    public UpcomingCommand(int dayRange) throws InvalidUpcomingInputException {
        if (dayRange < 1) {
            throw new InvalidUpcomingInputException("Please enter a valid number of days!");
        } else {
            this.dayRange = dayRange;
        }
    }

    /**
     * Executes the command to find timed tasks that occur within the range of days.
     * @param tasks The current TaskList.
     * @param ui The Ui object in use.
     * @param storage The Storage object in use.
     */
    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        assert ui != null : "Null UI";
        assert tasks != null : "Null Tasklist";
        StringBuilder output = new StringBuilder();
        int counter = 1;
        for (Task task : tasks.getTasks()) {
            if (task instanceof TimedTask) {
                if (checkIfInRange((TimedTask) task)) {
                    output.append(counter == 1 ? "" : "\n").append(counter).append(".").append(task);
                    counter++;
                }
            }
        }
        ui.setMessageUpcoming(output.toString(), counter - 1, dayRange);
    }

    /**
     * Checks whether the input TimedTask takes place within the number of days input.
     * @param timedTask The task to check.
     * @return A boolean indicating whether the task takes places within the number of days input.
     */
    protected boolean checkIfInRange(TimedTask timedTask) {
        assert timedTask instanceof Deadline || timedTask instanceof Event : "Non-timed task entered!";
        LocalDateTime time = timedTask.getDateTime();
        LocalDateTime newTime = time.minusDays(dayRange);
        return newTime.isBefore(LocalDateTime.now()) && time.isAfter(LocalDateTime.now());
    }
}
