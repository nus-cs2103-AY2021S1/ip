package duke.command;

import duke.*;
import duke.task.Event;
import duke.task.TaskList;

import java.io.IOException;

/**
 * AddEventCommand class that represents add event commands
 */
public class AddEventCommand extends Command {

    /**
     * AddEventCommand Class constructor
     * @param command the command from the user
     */
    public AddEventCommand(String command) {
        super(command);
    }

    /**
     * Method that execute the current AddEventCommand object
     * @param list TaskList object from the current Duke instance
     * @param ui    UI object from the current Duke instance
     * @param saveData Storage object from the current Duke instance
     */
    public void execute(TaskList list, Ui ui, Storage saveData) {
        try {
            if (this.command.trim().length() == 5) {
                throw new DukeException("☹ OOPS!!! Check event formatting, include description and /at.");
            } else if (!this.command.contains("/at")) {
                throw new DukeException("☹ OOPS!!! Check event formatting, include /at.");
            }
            String holder[] = this.command.split("event")[1].split("/at ");
            String description = holder[0].trim();
            String at = holder[1].trim();
            Event task = new Event(description, at);

            list.add(task);
            ui.saySomthing("Got it. I've added this task:\n" + task.toString() + "\n" + String.format("Now you have %d tasks in the list.", list.size()));

            String save = "E>0>"+description+">"+at;
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
