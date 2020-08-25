package Duke.Commands;
import Duke.Errors.DoneException;
import Duke.Errors.DukeException;
import Duke.Errors.FileAbsentException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;


import java.io.FileWriter;
import java.io.IOException;

/**
 * handles case when done is keyword
 */
public class DoneCommand extends Command {
    /**
     * assigns string to a value of string
     * @param string assigns string to this this.string
     */
    public DoneCommand(String string) {
        super(string);
    }

    private void rewrite(Storage storage, TaskList tasks, int ID) throws FileAbsentException {
        tasks.getAllTasks().get(ID - 1).setDone(true);
        System.out.println("   Nice! I've marked this task as done:");
        System.out.println("   " + tasks.getAllTasks().get(ID - 1).toString());
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
     * is used to complete done task and handle error
     * @param tasks to change the taskList as a task is completed
     * @param ui
     * @param storage to change the file as task is completed
     * @throws DukeException thrown if the ID is more than number of ID is absent
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (string.length() == 4 || string.length() == 5) {
            throw new DoneException(true, false);
        }else{
            int ID = Integer.parseInt(string.substring(5));
            if (ID > tasks.getAllTasks().size()) {
                throw new DoneException(false, false);
            } else {
                rewrite(storage, tasks, ID);
        }
        }
    }
}
