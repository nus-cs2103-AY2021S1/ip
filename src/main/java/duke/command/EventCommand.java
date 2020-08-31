package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command {
    private String taskName;
    private String by;

    public EventCommand(String taskName, String by) {
        super();
        this.cmd = CMD.EVENT;
        this.taskName = taskName;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.display("I PUT NEW TING IN DA LIST\n  " + taskList.addEvent(this.taskName, this.by)
                + "\nNAO U HAS " + taskList.getNumberOfTasks() + " FINGS IN DA LIST LULZIES");
        storage.save(taskList);
    }

    @Override
    public String toString() {
        return cmd.toString() + ": " + taskName + "(" + by + ")";
    }
}
