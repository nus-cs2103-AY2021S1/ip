package Duke.Commands;

import Duke.Errors.DukeException;
import Duke.Errors.TodoException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import Duke.Tasks.ToDo;

/**
 * has the method if ToDo is keyword deadline
 */
public class TodoCommand extends AddCommand{
    /**
     * Assigns string to a value of string
     *
     * @param input assigns string to this this.string
     */
    public TodoCommand(String input, int lengthOfKeyword) {
        super(input, lengthOfKeyword);
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
        try {
            if (isDescriptionAbsent()) {
                throw new TodoException();
            }
            ToDo t = new ToDo(todoDescription());
            return updateTaskList(storage, t, tasks);
        }catch (DukeException dukeException){
            ui.setDukeException(dukeException);
            throw dukeException;
        }
    }

    /**
     * checks whether the commandDescription contains the description
     *
     * @return true if description absent and false otherwise.
     */
    private boolean isDescriptionAbsent(){
        return commandDescription.length() == lengthOfKeyword || commandDescription.length() == lengthOfKeyword + 1; //since if the description is absent length is only 4 or 5
    }

    /**
     * returns the name of the task
     *
     * @return name of task
     */
    private String todoDescription(){
        return commandDescription.substring(lengthOfKeyword + 1);
    }

}

