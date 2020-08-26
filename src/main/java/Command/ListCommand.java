package Command;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        if (taskList.getTaskList().isEmpty()) {
            throw new DukeException("☹ OOPS !!! La lista está vacía. ¡Agregue una nueva tarea!");
        }
        ui.showList();
        for (int i = 0; i < taskList.getTaskList().size(); i++) {
            System.out.println((i + 1) + ". " + taskList.getTaskList().get(i).toString());
        }
    }
}
