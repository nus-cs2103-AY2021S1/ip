package Duke.Commands;

import Duke.Errors.DukeException;
import Duke.Errors.TodoException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import Duke.Tasks.todo;

import java.io.IOException;

public class TodoCommand extends AddCommand{
    public TodoCommand(String string) {
        super(string);
    }
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
