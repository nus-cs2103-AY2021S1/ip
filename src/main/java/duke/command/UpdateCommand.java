package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/** This class represents a user command to update a task. */
public class UpdateCommand extends Command {
    private int taskIndex;
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public UpdateCommand(int taskIndex, String description) {
        this.taskIndex = taskIndex;
        this.description = description;
    }

    public UpdateCommand(int taskIndex, LocalDate date) {
        this.taskIndex = taskIndex;
        this.date = date;
    }

    public UpdateCommand(int taskIndex, LocalTime startTime) {
        this.taskIndex = taskIndex;
        this.startTime = startTime;
    }

    public UpdateCommand(int taskIndex, LocalTime startTime, LocalTime endTime) {
        this.taskIndex = taskIndex;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Updates the task at index taskIndex in the given taskList
     *
     * @param taskList The TaskList object to make changes to or to get tasks from.
     * @param ui The Ui that saves messages to be sent to the user.
     * @param storage The Storage object to make changes to or to get tasks from.
     * @throws DukeException if user tries to update date or time of a ToDo object or endTime of a Deadline object
     * @throws IOException if error occurs while refreshing the storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        Task taskToUpdate = taskList.get(this.taskIndex - 1);
        if (this.date == null && this.startTime == null && this.description != null) {
            taskToUpdate.updateDescription(this.description);
        } else if (this.startTime == null && this.date != null) {
            taskToUpdate.updateDate(this.date);
        } else if (this.startTime != null) {
            if (this.endTime != null) {
                taskToUpdate.updateTime(this.startTime, this.endTime);
            } else {
                taskToUpdate.updateTime(this.startTime);
            }
        }
        ui.saveUpdateMessage(taskToUpdate, taskIndex);
        storage.refresh(taskList);
    }
}
