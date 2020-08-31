package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private String taskName;

    public TodoCommand(String taskName) {
        super();
        this.cmd = CMD.TODO;
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.display("I PUT NEW TING IN DA LIST\n  " + taskList.addTodo(this.taskName)
                    + "\nNAO U HAS " + taskList.getNumberOfTasks() + " FINGS IN DA LIST LULZIES");
        storage.save(taskList);
    }
}
