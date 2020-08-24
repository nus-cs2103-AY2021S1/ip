package Duke.Commands;
import Duke.Errors.DeleteException;
import Duke.Errors.DukeException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import Duke.Tasks.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(String string) {
        super(string);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.execute(tasks, ui, storage);
        if (string.length() == 4 || string.length() == 5) {
           throw new DeleteException(true, false);
        }else{
            int ID = Integer.parseInt(string.substring(7));
            if (ID > Task.getNum()) {
                throw new DeleteException(false, false);
            }else {
                Task task = Task.tasks.get(ID - 1);
                if(task.isDeleted()){
                    throw new DeleteException(false, true);
                }else{
                    Task.deleteDone(ID, storage.getFilePath());
                }
            }
        }


    }
}
