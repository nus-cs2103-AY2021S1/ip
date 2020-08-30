package command;

import java.time.LocalDateTime;
import java.util.Objects;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.DeadlineTask;
import task.Task;

public class DeadlineCommand extends Command {
    private String taskName;
    private LocalDateTime deadline;

    /**
     * Constructor for DeadlineCommand
     * @param taskName Name of the task
     * @param deadline deadline of the task
     */
    public DeadlineCommand(String taskName, LocalDateTime deadline) {
        super(CommandType.Deadline);
        this.taskName = taskName;
        this.deadline = deadline;
    }

    /**
     * Runs the command, modifying the contents of taskList, Ui or storage depending on the
     * implementation of child class
     *
     * @param taskList ArrayList of Tasks Objects
     * @param ui       Object of the Ui class
     * @param storage  Object of the Storage class
     * @throws DukeException Exception that occurs while executing the command
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deadlineTask = new DeadlineTask(this.taskName, this.deadline);
        taskList.addTask(deadlineTask);
        return ui.showAdd(deadlineTask, taskList.getSize());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeadlineCommand that = (DeadlineCommand) o;
        return Objects.equals(taskName, that.taskName) && Objects.equals(deadline, that.deadline);
    }
}
