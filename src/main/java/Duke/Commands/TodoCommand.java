package Duke.Commands;

import Duke.Errors.DukeException;
import Duke.Errors.TodoException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import Duke.Tasks.todo;

import java.io.IOException;

/**
 * has the method if todo is keyword deadline
 */
public class TodoCommand extends AddCommand{
    /**
     * assigns string to a value of string
     * @param string assigns string to this this.string
     */
    public TodoCommand(String string) {
        super(string);
    }

    /**
     * to add deadline into a task list in TaskList,
     * @param tasks to change the taskList if necessary
     * @param ui
     * @param storage to change the file in the if necessary
     * @throws DukeException whenever there is an error, no
     * description
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (string.length() == 4 || string.length() == 5) {
            throw new TodoException();
        } else {
            try {
                todo t = new todo(string.substring(5));
                update(storage, t, tasks);
            }catch (IOException i){

            }
        }
    }
}
