package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

public class DeadlineCommand implements Command {
    private final LocalDate deadlineTime;
    private final String deadlineDesc;

    /**
     * Creates a new DeadlineCommand object which will create a new {@code Deadline} object
     * when the execute() method is called.
     * @param deadlineDesc information about the {@code Deadline} to be added
     * @param deadlineTime time by which the {@code Deadline} is expected to be completed
     */
    public DeadlineCommand(String deadlineDesc, LocalDate deadlineTime) {
        this.deadlineDesc = deadlineDesc;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newTask = new Deadline(deadlineDesc, deadlineTime);
        tasks.addTask(newTask);
        return ui.displayTaskAdd(newTask, tasks.getCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
