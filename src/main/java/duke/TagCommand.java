package duke;

public class TagCommand extends Command {
    private final int index;
    private final String tag;

    public TagCommand(int index, String tag) {
        this.index = index;
        this.tag = tag;
    }

    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task task = list.tagTask(this.index, this.tag);
        String textOutput = "Nice! I've tagged this task with #" + this.tag + ":\n";
        textOutput += task;
        storage.updateTextFile(list);
        return ui.getLine(textOutput);
    }
}
