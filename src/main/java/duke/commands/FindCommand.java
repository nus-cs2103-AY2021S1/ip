package duke.commands;

import duke.Ui;
import duke.tasks.TaskManager;

/**
 * <code>duke.commands.FindCommand</code> inherits from the base class <code>duke.commands.Command</code>.
 * It handles the execution of finding duke.tasks given a particular keyword.
 */
public class FindCommand extends Command {

    /**
     * Asks for a keyword through the <code>duke.Ui</code> object and receives the user
     * input through the <code>Scanner</code> object from the parent class.
     * It then calls the method <code>findTask</code> with the <code>keyword</code>
     * passed as an argument. It then prints out the result.
     * @return <code>true</code>
     */
    @Override
    public boolean execute(String input) {
        String result = tm.findTask(input);
        setResponse(result);
        setDone();
        return true;
    }

    @Override
    public void init(TaskManager tm, Ui ui) {
        setResponse(ui.askForKeyword());
        setUtility(tm, ui);
    }
}
