package duke.commands;

import duke.Ui;
import duke.tasks.TaskManager;

/**
 * <code>FindCommand</code> inherits from the base class <code>Command</code>.
 * It handles the execution of finding tasks given a particular keyword.
 */
public class FindCommand extends Command {

    /**
     * Receives the keyword to search for and sets the response to be the result of the tasks found.
     * @param input the user input.
     * @return <code>true</code>
     */
    @Override
    public boolean execute(String input) {
        String result = tm.findTask(input);
        setResponse(result);
        setDone();
        return true;
    }

    /**
     * Sets the inital response to ask for the keyword to search for.
     * In addition, it sets the utility tools <code>tm</code> and <code>ui</code>.
     * @param tm the task manager.
     * @param ui the ui.
     */
    @Override
    public void init(TaskManager tm, Ui ui) {
        setResponse(ui.askForKeyword());
        setUtility(tm, ui);
    }
}
