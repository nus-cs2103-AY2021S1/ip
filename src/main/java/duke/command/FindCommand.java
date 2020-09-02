package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.NullFindInputException;

/**
 * FindCommand deals with find input.
 */
public class FindCommand extends Command {
    private String input;

    /**
     * Initiate FindCommand.
     * @param input User input
     * @throws NullFindInputException
     */
    public FindCommand(String input) throws NullFindInputException {

        //Removes all whitespaces and checks if input is empty
        if (input.replaceAll("\\s+", "").length() == 0) {
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
     * @param storage   Storage to update save file
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String tasksFound = taskList.findTasks(this.input);

        if (tasksFound.length() == 0) {
            return Ui.showNoTaskFound(this.input);
        } else {
            return Ui.showTaskFound(this.input, tasksFound);
        }
    }
}
