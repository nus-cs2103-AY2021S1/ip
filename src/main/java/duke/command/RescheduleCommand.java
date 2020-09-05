package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberException;
import duke.exception.RescheduleException;
import duke.task.DateTask;
import duke.task.Task;
import duke.task.ToDoTask;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.time.LocalDateTime;

public class RescheduleCommand extends Command {
    private int taskNumber;
    private LocalDateTime date;

    public RescheduleCommand(int taskNumber, LocalDateTime date) {
        this.taskNumber = taskNumber;
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidTaskNumberException();
        }

        Task task = tasks.getTask(taskNumber - 1);

        if (task instanceof ToDoTask) {
            throw new RescheduleException();
        } else if (task instanceof DateTask) {
            DateTask dateTask = (DateTask) task;
            dateTask.setDate(date);
            storage.changeTaskDateInFile(taskNumber, dateTask.getDateString());
            ui.sendMessage(ui.changeDateSuccess(task));
            return ui.changeDateSuccess(task);
        }

        return null;
    }
}
