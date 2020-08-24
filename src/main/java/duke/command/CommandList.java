package duke.command;

import duke.*;

/**
 * The command to list all task of a designated task list.
 */
public class CommandList implements Command {
    /**
     * Execute the command to list all tasks of a given <code>TaskList</code> object.
     * @param tasks the <code>TaskList</code> to operate on
     * @param ui the <code>Ui</code> to handle the interface updates
     */
    public void execute(TaskList tasks, Ui ui) { ui.printList(tasks.getList()); }

    /**
     * Return <code>false</code> since the command is not exit.
     * @return <code>false</code>
     */
    public boolean isExit() { return false; }
}
