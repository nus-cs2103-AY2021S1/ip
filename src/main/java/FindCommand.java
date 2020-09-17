/**
 * FindCommand class that executes find actions.
 * Extends from Command class.
 */

public class FindCommand extends Command {

    public FindCommand(Ui ui, TaskList taskList, String args) {
        super(ui, taskList, args);
    }

    /**
     * Executes actions for find command.
     * @return String containing a list of tasks found
     */
    @Override
    public String action() {
        String result;
        try {
            String word = args;
            result = ui.find(word, taskList);
            return result;
        } catch (DukeException e) {
            result = ui.printDukeError(e);
        }
        return result;
    }
}
