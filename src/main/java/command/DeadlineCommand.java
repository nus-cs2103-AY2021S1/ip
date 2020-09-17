package main.java.duke.command;

import main.java.duke.command.Command;
import main.java.duke.task.Deadline;
import main.java.duke.task.TaskList;
import main.java.duke.Ui;
import main.java.duke.Storage;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    String desc;
    LocalDate by;

    public DeadlineCommand(String desc, LocalDate by) {
        this.desc = desc;
        this.by = by;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(desc, by);
        taskList.addTask(deadline);
        ui.showAddTask(deadline);
        ui.showNumberOfTasksLeft(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
