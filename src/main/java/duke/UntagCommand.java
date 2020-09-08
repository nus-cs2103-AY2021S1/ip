package duke;

public class UntagCommand extends Command {
    private static final int ALL = -1;
    private final int index;
    private final String tag;

    public UntagCommand(int index, String tag) {
        this.index = index;
        this.tag = tag;
    }

    public UntagCommand(String tag) {
        this(ALL, tag);
    }

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
