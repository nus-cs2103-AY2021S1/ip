package duke;

public class UntagCommand extends Command {
    private static final int ALL = -1;
    private final int index;
    private final String tag;

    /** Creates a new UntagCommand
     *
     * @param index index of task for tag to be removed
     * @param tag tag of task that must be removed
     */
    public UntagCommand(int index, String tag) {
        this.index = index;
        this.tag = tag;
    }

    /** Creates a new UntagCommand, to untag all tasks with such tag.
     *
     * @param tag tag of task that must be removed
     */
    public UntagCommand(String tag) {
        this(ALL, tag);
    }

    /**
     * Executes the untag command by processing the input TaskList, Ui, and Storage instances.
     *
     * @param list a TaskList containing all Duke's current tasks.
     * @param ui a user interface in charge of Duke's I/O.
     * @param storage a storage system that handles .txt file manipulation.
     */
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String textOutput = "Nice! I've removed the tag #" + this.tag;
        if (this.index != ALL) {
            Task task = list.untagTask(this.index, this.tag);
            textOutput += " from this task:\n";
            textOutput += task;
        } else {
            TaskList taskList = list.untagAllTasks(this.tag);
            textOutput += " from these tasks:\n";
            textOutput += taskList;
        }
        storage.updateTextFile(list);
        return ui.getLine(textOutput);
    }
}
