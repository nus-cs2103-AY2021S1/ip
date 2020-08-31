package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

import java.time.LocalDate;

public class DeadlineCommand implements Command {
    private final LocalDate deadlineTime;
    private final String deadlineDesc;

    public DeadlineCommand(String deadlineDesc, LocalDate deadlineTime) {
        this.deadlineDesc = deadlineDesc;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newTask = new Deadline(deadlineDesc, deadlineTime);
        tasks.addTask(newTask);
        ui.displayTaskAdd(newTask, tasks.getCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
