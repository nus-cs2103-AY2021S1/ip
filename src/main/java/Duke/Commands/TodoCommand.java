package Duke.Commands;

import Duke.Errors.DukeException;
import Duke.Errors.FileAbsentException;
import Duke.Errors.TodoException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import Duke.Tasks.ToDo;

import java.io.IOException;

/**
 * has the method if ToDo is keyword deadline
 */
public class TodoCommand extends AddCommand{
    /**
     * Assigns string to a value of string
     *
     * @param string assigns string to this this.string
     */
    public TodoCommand(String string) {
        super(string);
    }

    /**
     * Adds deadline into a task list in TaskList.
     *
     * @param tasks to change the taskList if necessary
     * @param ui
     * @param storage to change the file in the if necessary
     * @return String returns the string of the output that informs the action has been complete.
     * @throws DukeException whenever there is an error, no
     * description
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (isDescriptionAbsent()) {
            throw new TodoException();
        } else {
            try {
                ToDo t = new ToDo(todoDescription());
                return updateTaskList(storage, t, tasks);
            }catch (IOException i){
                throw new FileAbsentException(storage.getFilePath());
            }
        }
    }

    /**
     * checks whether the commandDescription contains the description
     *
     * @return true if description absent and false otherwise.
     */
    private boolean isDescriptionAbsent(){
        return commandDescription.length() == 4 || commandDescription.length() == 5; //since if the description is absent length is only 4 or 5
    }

    /**
     * returns the name of the task
     *
     * @return name of task
     */
    private String todoDescription(){
        return commandDescription.substring(5);
    }

}

