package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.IncompleteCommandException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;


public class ListCommand extends Command{

    public ListCommand(String description){
        super(description);
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        String[] inputList = description.split(" ", 2);
        if ( inputList.length > 1){
            throw new IncompleteCommandException();
        }

        StringBuffer result = new StringBuffer();
        //to add in the starting line of the section
        result.append("Here are the tasks in your list:\n");

        for (int i = 0; i < taskList.getSize(); i++) {
            // getting the current task
            Task currentTask = taskList.getTask(i);

            // adding the current task into the taskList
            result.append((i + 1) + ". " + currentTask.toString() + "\n");
        }
        ui.lineFormatter(result.toString());
        return result.toString();
    }

    @Override
    public boolean isComplete(){
        return false;
    }
}
