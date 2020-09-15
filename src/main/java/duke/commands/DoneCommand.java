package duke.commands;

import java.io.FileWriter;
import java.io.IOException;

import duke.errors.DoneException;
import duke.errors.DukeException;
import duke.errors.FileAbsentException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;

/**
 * Handles case when done is keyword
 */
public class DoneCommand extends Command {
    /**
     * assigns string to a value of string
     *
     * @param input assigns string to this this.string
     * @param lengthOfKeyword assigns this to this.lengthOfKeyword
     */
    public DoneCommand (String input, int lengthOfKeyword) {
        super(input, lengthOfKeyword);
    }

    /**
     * Completes done task and handle error
     *
     * @param tasks to change the taskList as a task is completed
     * @param ui to store the DukeException that may be thrown if there is an error in user input
     * @param storage to change the file as task is completed
     * @return String returns the string of the output that informs the done action has been complete.
     * @throws DukeException thrown if the ID is more than number of ID is absent
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            return process(tasks, storage);
        } catch (DukeException dukeException) {
            ui.setDukeException(dukeException);
            throw dukeException;
        }
    }

    /**
     * Returns a String if the input is given in the correct order, else Exception is thrown
     *
     * @param tasks is used to check whether the tasks to be found is present in TaskList, to mark as done
     * @param storage is used to update the file in storage that contains current tasks, to mark that task as done
     * @return String if the user input is correct
     * @throws DukeException if the user input is wrong
     */
    private String process(TaskList tasks, Storage storage) throws DukeException {
        if (isNumberOrDescriptionAbsent()) {
            throw new DoneException(true, false); //when number is absent
        } else {
            int iD = Integer.parseInt(userInput.substring(lengthOfKeyword + 1));
            if (isNumberNotInList(iD, tasks)) {
                throw new DoneException(false, true); //when number is not in list
            } else {
                return rewrite(storage, tasks, iD); //where the file in Storage is updated and TaskList is updated
            }
        }
    }
    /**
     * Returns whether the task is present in the list.
     *
     * @param iD of task to be removed
     * @param tasks from which the task is to be removed.
     * @return true if the task is not present in list.
     */
    private boolean isNumberNotInList(int iD, TaskList tasks) {
        return iD > tasks.getAllTasks().size(); //since ID cannot be more that number of tasks present
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
    private String newInputInStorageFIle(TaskList tasks) {
        String s = "";
        for (int i = 0; i < tasks.getAllTasks().size(); i++) {
            s = s + tasks.getAllTasks().get(i).inputListFormat() + "\n";
            //new taskList String since done is being set for task with iD mentioned by user
        }
        return s;
    }

    /**
     * Returns the String informing that the task that is marked as done
     *
     * @param tasks uses to give the current number of tasks.
     * @param iD uses to get the task to mark done.
     * @return String informing that the task is marked as done.
     */
    private String doneMessage(TaskList tasks, int iD) {
        return "   Nice! I've marked this task as done:\n" + "   "
                + tasks.getAllTasks().get(iD - 1).toString(); //gives the doneMessage
    }
    /**
     * This returns the string that the task has been deleted adn also updated the TakList.
     *
     * @param storage in which the file is updated.
     * @param tasks used to update the task for the task to mark as done.
     * @param iD of the task to mark as done.
     * @return String of done message
     * @throws DukeException throws if file is absent
     */
    private String rewrite(Storage storage, TaskList tasks, int iD) throws DukeException {
        tasks.getAllTasks().get(iD - 1).setDone(true); //sets the task at (ID - 1) as done
        String s = newInputInStorageFIle(tasks); //new List for storage file
        try {
            updateTaskInStorage(s, storage); //updates the TaskList and the file in storage file
            return doneMessage(tasks, iD); // returns done message
        } catch (FileAbsentException f) {
            throw new FileAbsentException(storage.getFilePath());
        }
    }

}
