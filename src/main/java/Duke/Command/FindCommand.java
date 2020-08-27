package Duke.Command;

import Duke.*;
import Duke.Exception.*;

/**
 * FindCommand deals with find input.
 */
public class FindCommand extends Command {
    String input;

    /**
     * Initiate FindCommand.
     * @param input User input
     * @throws NullFindInputException
     */
    public FindCommand(String input) throws NullFindInputException {

        //Removes all whitespaces and checks if input is empty
        if (input.replaceAll("\\s+","").length() == 0) {
            throw new NullFindInputException();
        }

        this.input = input.startsWith(" ")
                ? input.substring(1)
                : input;
    }
    @Override
    public boolean isExited() {
        return false;
    }


    /**
     * Execute find command.
     * @param taskList  TaskList for delete to be performed
     * @param ui        User interface used
     * @param storage   Storage to update save file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String tasksFound = taskList.findTasks(this.input);

        if (tasksFound.length() == 0) {
            ui.showNoTaskFound(this.input);
        } else {
            ui.showTaskFound(this.input, tasksFound);
        }
    }
}
