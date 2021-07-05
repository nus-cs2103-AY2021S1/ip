package duke;

public class TagCommand extends Command {
    private final int index;
    private final String tag;

    /**
     * Initialises a new tag command.
     * @param index of task to be tagged.
     * @param tag tag to be added.
     */
    public TagCommand(int index, String tag) {
        this.index = index;
        this.tag = tag;
    }

    /**
     * Executes the tag command by processing the input TaskList, Ui, and Storage instances.
     *
     * @param list a TaskList containing all Duke's current tasks.
     * @param ui a user interface in charge of Duke's I/O.
     * @param storage a storage system that handles .txt file manipulation.
     */
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task task = list.tagTask(this.index, this.tag);
        String textOutput = "Nice! I've tagged this task with #" + this.tag + ":\n";
        textOutput += task;
        storage.updateTextFile(list);
        return ui.getLine(textOutput);
    }
}
