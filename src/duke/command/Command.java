package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Command abstract class that represents specific commands
 */
public abstract class Command {
    boolean isExit;
    String command;

    /**
     * Command Abstract Class constructor
     * @param command the command from the user
     */
    Command(String command){
        this.command = command;
        this.isExit = false;
    }

    /**
     * Method that execute the current Command object
     * @param list TaskList object from the current Duke instance
     * @param ui    UI object from the current Duke instance
     * @param saveData Storage object from the current Duke instance
     */
    public void execute(TaskList list, Ui ui, Storage saveData){};

    /**
     * Method that return isExit of the current Command
     * @return boolean object showing if Duke should terminate
     */
    public boolean isExit(){
        return isExit;
    };
}
