package duke.commands;

import duke.exceptions.DoneException;
import duke.exceptions.DukeException;
import duke.exceptions.TaskAlreadyDoneException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class DoneCommand extends Command{

    public DoneCommand(String description){
        super(description);
    }

    public String execute (TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputList = description.trim().split(" ", 2);

        int currentIndex = Integer.parseInt(inputList[1]) - 1;
        if (currentIndex + 1 > taskList.getSize() || currentIndex + 1 <= 0){
            throw new DoneException(currentIndex, taskList.getSize());
        } else {
            Task task = taskList.getTask(currentIndex);
            // to check if  the task is already done
            if (task.getStatus()){
                throw new TaskAlreadyDoneException(task);

            } else {
                taskList.getTask(currentIndex).markAsDone();
                return ui.taskDone(taskList.getTask(currentIndex));
            }
        }
    }

    @Override
    public boolean isComplete(){
        return false;
    }
}
