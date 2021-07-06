package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.tool.TaskList;

public class RescheduleCommand implements Command {

    private final int targetIndex;
    private final LocalDateTime targetDate;
    private Task rescheduledTask;

    /**
     * Creates an instance of reschedule command.
     *
     * @param targetIndex Index of task that needs to be rescheduled.
     * @param targetDate Expect date of the task.
     */
    public RescheduleCommand(int targetIndex, LocalDateTime targetDate) {
        this.targetIndex = targetIndex;
        this.targetDate = targetDate;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        rescheduledTask = tasks.getTasks().get(targetIndex - 1).reschedule(this.targetDate);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getResponse() {
        assert !isExit();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

        return "I have rescheduled: \n \t"
                + rescheduledTask.toString() + " to "
                + targetDate.format(formatter);
    }
}
