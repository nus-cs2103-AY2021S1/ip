package duke.commands;

import duke.exceptions.DeleteException;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command{

    public DeleteCommand(String description){
        super(description);
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputList = description.trim().split(" ", 2);

        int currentIndex = Integer.parseInt(inputList[1]) - 1;
        if  (currentIndex + 1 > taskList.getSize() || currentIndex + 1 <= 0) {
            throw new DeleteException(currentIndex, taskList.getSize());
        } else {
            Task deletedTask = taskList.removeTask(currentIndex);
            return ui.taskDeleted(deletedTask);
        }
    }

    public boolean isComplete(){
        return false;
    }
}
