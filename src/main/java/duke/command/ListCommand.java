package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.IncompleteDukeCommandException;
import duke.task.TaskList;

/**
 * The {@code DeleteAllCommand} class represents a command to print all contents of a {@link TaskList}.
 */
public class ListCommand extends Command {

    private String printout;

    /**
     * Executes this {@code ListCommand}.
     *
     * @param list    the currently loaded {@link TaskList} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    public void execute(TaskList list, Storage storage) {
        this.printout = list.listItems();
        super.completed = true;
    }

    /**
     * Prints the result of executing this {@code ListCommand}.
     *
     * @param ui the {@link Ui} instance to use for formatting.
     * @throws DukeException
     */
    public void printFeedback(Ui ui) throws DukeException {
        if (super.completed) {
            ui.formattedPrint(ui.prependIndent(this.printout, 1));
        } else {
            throw new IncompleteDukeCommandException("List command was not completed.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }

}
