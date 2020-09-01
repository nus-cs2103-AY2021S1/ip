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

    /**
     * Executes the TODO command, adds a TODO task to the existing taskList and saves
     * the updated taskList to a log file. TODO tasks do not take in any deadlines.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.display("I PUT NEW TING IN DA LIST\n  " + taskList.addTodo(this.taskName)
                    + "\nNAO U HAS " + taskList.getNumberOfTasks() + " FINGS IN DA LIST LULZIES");
        storage.save(taskList);
    }

    @Override
    public String toString() {
        return this.cmd.toString() + ": " + this.taskName;
    }
}
