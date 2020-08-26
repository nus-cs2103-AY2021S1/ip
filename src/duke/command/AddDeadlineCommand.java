package duke.command;


import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.TaskList;

import java.io.IOException;
/**
 * AddDeadlineCommand class that represents add deadline commands
 */
public class AddDeadlineCommand extends Command {

    /**
     * AddDeadlineCommand Class constructor
     * @param command the command from the user
     */
    public AddDeadlineCommand(String command) {
        super(command);
    }

    /**
     * Method that execute the current AddDeadlineCommand object
     * @param list TaskList object from the current Duke instance
     * @param ui    UI object from the current Duke instance
     * @param saveData Storage object from the current Duke instance
     */
    public void execute(TaskList list, Ui ui, Storage saveData) {
        try {
            if (this.command.trim().length() == 8) {
                throw new DukeException("☹ OOPS!!! Check deadline formatting, include description and /by.");
            } else if (!this.command.contains("/by")) {
                throw new DukeException("☹ OOPS!!! Check deadline formatting, include /by.");
            }
            String holder[] = this.command.split("deadline")[1].split("/by ");
            String description = holder[0].trim();
            String by = holder[1].trim();
            Deadline task = new Deadline(description, by);
            list.add(task);
            ui.saySomthing("Got it. I've added this task:\n" + task.toString() + "\n" + String.format("Now you have %d tasks in the list.", list.size()));
            String save = "D>0>"+description+">"+by;
            saveData.addTask(save);
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
