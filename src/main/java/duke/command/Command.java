package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command abstract class that represents specific commands
 */
public abstract class Command {
    private boolean isExit;
    private String command;

    /**
     * Command Abstract Class constructor
     *
     * @param command the command from the user
     */
    Command(String command) {
        this.command = command;
        this.isExit = false;
    }

    /**
     * Getter function that return isExit attribute
     *
     * @return isExit
     */
    boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Getter function that return command attribute
     *
     * @return command
     */
    String getCommand() {
        return this.command;
    }
    /**
     * Method that execute the current Command object
     *
     * @param list     TaskList object from the current Duke instance
     * @param ui       UI object from the current Duke instance
     * @param saveData Storage object from the current Duke instance
     */
    public String execute(TaskList list, Ui ui, Storage saveData) {
        return null;
    }

    /**
     * Method check for duplicate task
     *
     * @param list     TaskList object from the current Duke instance
     * @param description   description of current task
     * @return true if task is duplicated otherwise false
     */
    public boolean detectDuplicate(TaskList list, String description) {
        for (Task task: list.getList()) {
            if (description.equals(task.getDescription())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that return isExit of the current Command
     *
     * @return boolean object showing if Duke should terminate
     */
    public boolean isExit() {
        return isExit;
    }
}
