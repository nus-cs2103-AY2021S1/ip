package Duke.Commands;
import Duke.Errors.DoneException;
import Duke.Errors.DukeException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import Duke.Tasks.Task;

public class DoneCommand extends Command {
    public DoneCommand(String string) {
        super(string);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.execute(tasks, ui, storage);
        if (string.length() == 4 || string.length() == 5) {
            throw new DoneException(true, false);
        }else{
            int ID = Integer.parseInt(string.substring(5));
            if (ID > Task.getNum()) {
                throw new DoneException(false, false);
            } else {
            Task task = Task.tasks.get(ID - 1);
            if (task.isDeleted()) {
                throw new DoneException(false, true);
            } else {
                Task.setDone(ID, storage.getFilePath());
            }
        }
        }
    }
}
