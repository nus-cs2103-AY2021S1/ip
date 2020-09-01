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

    private String rewrite(Storage storage, TaskList tasks, int ID)  throws DukeException{
        System.out.println("   Noted. I've removed this task:\n" +
                "   " + tasks.getAllTasks().get(ID - 1).toString() + "\n" +
                "  Now you have " + (tasks.getAllTasks().size() - 1) + " tasks in the list.");
        tasks.getAllTasks().remove(ID - 1);
        String s = "";
        for(int i = 0; i < tasks.getAllTasks().size(); i++){
            s = s + tasks.getAllTasks().get(i).inputListFormat() + "\n";
        }
        try {
            FileWriter fw = new FileWriter(storage.getFilePath());
            fw.write(s);
            fw.close();
           return "   Noted. I've removed this task:\n" +
            "   " + tasks.getAllTasks().get(ID - 1).toString() + "\n" +
            "  Now you have " + (tasks.getAllTasks().size() - 1) + " tasks in the list.";
        } catch (IOException i) {
            throw new FileAbsentException(storage.getFilePath());
        }
    }

    /**
     * Deletes task and handles error
     *
     * @param tasks to change the taskList since item is deleted
     * @param ui
     * @param storage to change the file since item is deleted
     * @return String returns the string of the output that informs the delete action has been complete.
     * @throws DukeException thrown if the ID is more than number of ID is absent
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (commandDescription.length() == 4 || commandDescription.length() == 5) {
           throw new DeleteException(true);
        }else{
            int ID = Integer.parseInt(commandDescription.substring(7));
            if (ID > tasks.getAllTasks().size()) {
                throw new DeleteException(false);
            }else {
                return rewrite(storage, tasks, ID);
            }
        }
    }

}
