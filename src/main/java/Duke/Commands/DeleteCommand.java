package Duke.Commands;
import Duke.Errors.DeleteException;
import Duke.Errors.DukeException;
import Duke.Errors.FileAbsentException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import Duke.Tasks.Task;

import java.io.FileWriter;
import java.io.IOException;

public class DeleteCommand extends Command {
    public DeleteCommand(String string) {
        super(string);
    }

    public void rewrite(Storage storage, TaskList tasks, int ID) throws FileAbsentException {
        System.out.println("   Noted. I've removed this task:");
        System.out.println("   " + tasks.getAllTasks().get(ID - 1).toString());
        System.out.println("  Now you have " + (tasks.getAllTasks().size() - 1) + " tasks in the list.");
        tasks.getAllTasks().remove(ID - 1);
        String s = "";
        for(int i = 0; i < tasks.getAllTasks().size(); i++){
            s = s + tasks.getAllTasks().get(i).inputListFormat() + "\n";
        }
        try {
            FileWriter fw = new FileWriter(storage.getFilePath());
            fw.write(s);
            fw.close();
        } catch (IOException i) {
            throw new FileAbsentException(storage.getFilePath());
        }
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
                    rewrite(storage, tasks, ID);
                }
            }
        }


    }
}
