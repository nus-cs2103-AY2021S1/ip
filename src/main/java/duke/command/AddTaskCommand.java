package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class AddTaskCommand extends Command {

    protected String input;

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        addTask(taskList, input);
        storage.saveTasks(taskList.getList());
        taskList.printNewTask();
    }

    public abstract void addTask(TaskList taskList, String input) throws DukeException;
}
