package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.task.Tag;
import duke.task.Task;

public class TagCommand extends Command {

    private Tag tag;
    private int index;

    public TagCommand(int index, Tag tag) {
        this.tag = tag;
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        Task toBeTagged = tasks.get(index);
        toBeTagged.addTag(tag);
        return "I've tagged this task with " + tag.toString() + "!";
    }
}
