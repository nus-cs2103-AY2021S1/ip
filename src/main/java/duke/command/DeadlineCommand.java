package duke.command;

import duke.task.Deadline;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

import java.time.LocalDate;

/**
 * Command for adding a new <code>Deadline</code> task to a <code>TaskList</code>.
 */
public class DeadlineCommand extends Command {
    String desc;
    LocalDate by;

    public DeadlineCommand(String desc, LocalDate by) {
        this.desc = desc;
        this.by = by;
    }

    public String execute(TaskList taskList, Storage storage) {
        Deadline deadline = new Deadline(desc, by);
        taskList.addTask(deadline);
        return (Ui.showAddTask(deadline) + Ui.showNumberOfTasksLeft(taskList));
    }

    public boolean isExit() {
        return false;
    }
}
