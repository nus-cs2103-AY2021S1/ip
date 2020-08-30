package duke.commands;

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
    public boolean execute() {
        ui.askForKeyword();
        String keyword = sc.nextLine();
        String result = tm.findTask(keyword);
        ui.print(result);
        return true;
    }
}
