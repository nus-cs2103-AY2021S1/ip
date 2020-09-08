package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

public class DeadlineCommand implements Command {
    private final LocalDate deadlineTime;
    private final String deadlineDesc;

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
