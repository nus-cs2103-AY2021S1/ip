package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * FindTaskCommand class that represents find task commands
 */
public class FindTaskCommand extends Command {

    /**
     * FindTaskCommand Class constructor
     * @param command the command from the user
     */
    public FindTaskCommand(String command) {
        super(command);
    }

    /**
     * Method that execute the current FindTaskCommand object
     * @param list TaskList object from the current Duke instance
     * @param ui    UI object from the current Duke instance
     * @param saveData Storage object from the current Duke instance
     */
    public void execute(TaskList list, Ui ui, Storage saveData) {
        try {
            if (this.command.trim().length() == 4) {
                throw new DukeException("☹ OOPS!!! Check find formatting, include keyword");
            }
            String keyword = this.command.substring(5);
            TaskList searchedList = new TaskList();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDescription().contains(keyword)) {
                    searchedList.add(list.get(i));
                }
            }
            ui.showList(searchedList);
        } catch (DukeException e) {
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
