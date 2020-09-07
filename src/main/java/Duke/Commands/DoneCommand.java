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

    /**
     * Completes done task and handle error
     * @param tasks to change the taskList as a task is completed
     * @param ui
     * @param storage to change the file as task is completed
     * @return String returns the string of the output that informs the done action has been complete.
     * @throws DukeException thrown if the ID is more than number of ID is absent
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (isNumberAbsent()) {
            throw new DoneException(true, false); //when number is absent
        }else{
            int ID = Integer.parseInt(commandDescription.substring(5));
            if (isNumberNotInList(ID, tasks)) {
                throw new DoneException(false, true); //when number is not in list
            } else {
                return rewrite(storage, tasks, ID); //where the file in Storage is updated and TaskList is updated
            }
        }
    }
    /**
     * Returns whether the number is present.
     *
     * @return true is the number is absent and false if number is present.
     */
    private boolean isNumberAbsent(){
        return commandDescription.length() == 4 || commandDescription.length() == 5; // keyword is absent if user input is only 4/5
    }

    /**
     * Returns whether the task is present in the list.
     *
     * @param ID of task to be removed
     * @param tasks from which the task is to be removed.
     * @return true if the task is not present in list.
     */
    private boolean isNumberNotInList(int ID, TaskList tasks){
        return ID > tasks.getAllTasks().size(); //since ID cannot be more that number of tasks present
    }

    /**
     * updates the the file in storage after task is marked as done.
     *
     * @param newList where this is the new input replaces the old input in the file.
     * @param storage which contains file to be changed.
     * @throws FileAbsentException when the file to be updated is absent in Storage.
     */
    private void updateTaskInStorage(String newList, Storage storage) throws FileAbsentException {
        try {
            FileWriter fw = new FileWriter(storage.getFilePath());
            fw.write(newList); //updates task list to newList since one task is marked as done
            fw.close();
        } catch (IOException i) {
            throw new FileAbsentException(storage.getFilePath());
        }
    }

    /**
     * gives the string for the new task list
     *
     * @param tasks marks the task as done
     * @return the string for the new task list
     */
    private String newInputInStorageFIle(TaskList tasks){
        String s = "";
        for(int i = 0; i < tasks.getAllTasks().size(); i++){
            s = s + tasks.getAllTasks().get(i).inputListFormat() + "\n"; //new taskList String since done is being set for task with ID mentioned by user
        }
        return s;
    }

    /**
     * Returns the String informing that the task that is marked as done
     *
     * @param tasks uses to give the current number of tasks.
     * @param ID uses to get the task to mark done.
     * @return String informing that the task is marked as done.
     */
    private String doneMessage(TaskList tasks, int ID){
        return "   Nice! I've marked this task as done:\n" +
                "   " + tasks.getAllTasks().get(ID - 1).toString(); //gives the doneMessage
    }
    /**
     * This returns the string that the task has been deleted adn also updated the TakList.
     *
     * @param storage in which the file is updated.
     * @param tasks used to update the task for the task to mark as done.
     * @param ID of the task to mark as done.
     * @return
     * @throws DukeException throws if file is absent
     */
    private String rewrite(Storage storage, TaskList tasks, int ID) throws DukeException {
        tasks.getAllTasks().get(ID - 1).setDone(true); //sets the task at (ID - 1) as done
        String s = newInputInStorageFIle(tasks); //new List for storage file
        try {
            updateTaskInStorage(s, storage); //updates the TaskList and the file in storage file
            return doneMessage(tasks, ID); // returns done message
        } catch (FileAbsentException f) {
            throw new FileAbsentException(storage.getFilePath());
        }
    }



}
