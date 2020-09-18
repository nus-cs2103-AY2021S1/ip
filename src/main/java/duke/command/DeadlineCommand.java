package duke.command;

import duke.command.Command;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    String desc;
    LocalDate by;

    public DeadlineCommand(String desc, LocalDate by) {
        this.desc = desc;
        this.by = by;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(desc, by);
        taskList.addTask(deadline);
        return (ui.showAddTask(deadline) + ui.showNumberOfTasksLeft(taskList));
    }

    public boolean isExit() {
        return false;
    }
}
