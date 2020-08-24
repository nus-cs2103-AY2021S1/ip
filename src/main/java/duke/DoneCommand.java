package duke;

/**
 * Represents a done command in Duke.
 */
public class DoneCommand extends Command {
    private final int index;

    /**
     * Creates a new DoneCommand instance.
     *
     * @param index index of task that was done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the done command by processing the input TaskList, Ui, and Storage instances.
     *
     * @param list a TaskList containing all Duke's current tasks.
     * @param ui a user interface in charge of Duke's I/O.
     * @param storage a storage system that handles .txt file manipulation.
     */
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task task = list.setDone(this.index);
        String textOutput = "Nice! I've marked this task as done:\n";
        textOutput += task;
        storage.updateTextFile(list);
        ui.printLine(textOutput);
    }
}
