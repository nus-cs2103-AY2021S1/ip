package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * DeleteTaskCommand class that represents delete task commands
 */
public class DeleteTaskCommand extends Command {

    /**
     * DeleteTaskCommand Class constructor
     * @param command the command from the user
     */
    public DeleteTaskCommand(String command) {
        super(command);
    }

    /**
     * Method that execute the current DeleteTaskCommand object
     * @param list TaskList object from the current Duke instance
     * @param ui    UI object from the current Duke instance
     * @param saveData Storage object from the current Duke instance
     */
    public void execute(TaskList list, Ui ui, Storage saveData) {
        try {
            if (this.command.trim().length() == 6) {
                throw new DukeException("☹ OOPS!!! Check delete formatting, include which task to delete.");
            } else if (Character.getNumericValue(this.command.charAt(7)) > list.size() || Character.getNumericValue(this.command.charAt(7)) == 0) {
                throw new DukeException("☹ OOPS!!! Task not in the list");
            }
            int index = Character.getNumericValue(this.command.charAt(7));
            Task toRemove = list.get(index-1);
            list.remove(index-1);
            ui.saySomthing("Noted. I've removed this task:\n" + toRemove.toString() + "\n" + String.format("Now you have %d tasks in the list.", list.size()));
            saveData.deleteTask(index);
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
