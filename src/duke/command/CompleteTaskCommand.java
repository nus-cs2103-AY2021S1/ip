package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * CompleteTaskCommand class that represents complete task commands
 */
public class CompleteTaskCommand extends Command {

    /**
     * CompleteTaskCommand Class constructor
     * @param command the command from the user
     */
    public CompleteTaskCommand(String command) {
        super(command);
    }

    /**
     * Method that execute the current CompleteTaskCommand object
     * @param list TaskList object from the current Duke instance
     * @param ui    UI object from the current Duke instance
     * @param saveData Storage object from the current Duke instance
     */
    public void execute(TaskList list, Ui ui, Storage saveData) {
        try {
            if (this.command.trim().length() == 4) {
                throw new DukeException("☹ OOPS!!! Check done formatting, include which task to complete.");
            } else if (Character.getNumericValue(this.command.charAt(5)) > list.size() || Character.getNumericValue(this.command.charAt(5)) == 0) {
                throw new DukeException("☹ OOPS!!! Task not in the list");
            }
            int index = Character.getNumericValue(this.command.charAt(5));
            list.get(index-1).markAsDone();
            ui.saySomthing("Nice! I've marked this task as done:\n" + list.get(index-1).toString());
            saveData.completeTask(index);
        } catch (DukeException | IOException e) {
            ui.saySomthing(e.getMessage());
        }
    }

    /**
     * Method that return isExit of the current Command
     * @return boolean object showing if Duke should terminate
     */
    @Override
    public boolean isExit() {
        return isExit;
    }
}
