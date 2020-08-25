package Duke.Commands;

import Duke.Errors.DukeException;

import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;

/**
 * this is an abstract class used for polymorphism
 */
abstract public class Command {
    /**
     * string which contains information on task and details to perform task
     * exit is used to tell whether program terminates, where id true, it terminates
     */
    public String string;
    boolean exit = false;

    /**
     * used to assign string to a value
     * @param string assigns this.string to string
     */
    Command(String string){
        this.string = string;
    }

    /**
     * gets value of exit
     * @return exit
     */
    public boolean isExit(){
        return exit;
    }

    /**
     * executes the necessary task
     * @param tasks used to access tasks in its list and change if necessary
     * @param ui
     * @param storage to change the input there if necessary
     * @throws DukeException if there are exceptions present
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
