package duke;

public class FindTagCommand extends Command {
    private final String tag;

    public FindTagCommand(String tag) {
        this.tag = tag;
    }

    /**
     * Executes the Find Tag command by processing the input TaskList, Ui, and Storage instances.
     *
     * @param list a TaskList containing all Duke's current tasks.
     * @param ui a user interface in charge of Duke's I/O.
     * @param storage a storage system that handles .txt file manipulation.
     */
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        TaskList taskList = list.findTag(this.tag);
        String textOutput = "Here are your tasks tagged with #" + this.tag + ":\n";
        textOutput += taskList;
        storage.updateTextFile(list);
        return ui.getLine(textOutput);
    }
}
