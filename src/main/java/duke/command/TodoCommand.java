package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.task.ToDo;

/**
 * The TodoCommand class contains methods pertaining to the TodoCommand.
 *
 *  @author  Yen Pin Hsuan
 *  @version 1.0
 */
public class TodoCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            ToDo todo = ui.getToDo();
            taskList.addTask(todo);
            ui.addTask(todo, taskList);
            storage.addData(todo.store());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
