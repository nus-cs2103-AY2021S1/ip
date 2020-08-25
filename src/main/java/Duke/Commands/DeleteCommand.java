package Duke.Commands;

import Duke.Errors.DeleteException;
import Duke.Errors.DukeException;
import Duke.Errors.FileAbsentException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;


import java.io.FileWriter;
import java.io.IOException;

/**
 * handles the case when the keyword is delete
 */
public class DeleteCommand extends Command {
    /**
     * assigns string to a value of string
     * @param string assigns string to this this.string
     */
    public DeleteCommand(String string) {
        super(string);
    }

    private void rewrite(Storage storage, TaskList tasks, int ID) throws FileAbsentException {
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

    /**
     * used to do deleted task and handle error
     * @param tasks to change the taskList since item is deleted
     * @param ui
     * @param storage to change the file since item is deleted
     * @throws DukeException thrown if the ID is more than number of ID is absent
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (string.length() == 4 || string.length() == 5) {
           throw new DeleteException(true, false);
        }else{
            int ID = Integer.parseInt(string.substring(7));
            if (ID > tasks.getAllTasks().size()) {
                throw new DeleteException(false, false);
            }else {
                rewrite(storage, tasks, ID);
            }
        }


    }
}
