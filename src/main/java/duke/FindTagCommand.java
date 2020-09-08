package duke;

public class FindTagCommand extends Command {
    private final String tag;

    public FindTagCommand(String tag) {
        this.tag = tag;
    }

    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        TaskList taskList = list.findTag(this.tag);
        String textOutput = "Here are your tasks tagged with #" + this.tag + ":\n";
        textOutput += taskList;
        storage.updateTextFile(list);
        return ui.getLine(textOutput);
    }
}
