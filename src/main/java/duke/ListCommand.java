package duke;

/**
 * Represents a list command in Duke.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by processing the input TaskList, Ui, and Storage instances.
     *
     * @param list a TaskList containing all Duke's current tasks.
     * @param ui a user interface in charge of Duke's I/O.
     * @param storage a storage system that handles .txt file manipulation.
     */
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        if (list.isEmpty()) {
            throw new DukeException("OOPS!!! I'm sorry, your list is empty :<");
        }
        String textOutput = "Here is the complete list of your task: \n";
        textOutput += list.toString();
        ui.printLine(textOutput);
    }
}
