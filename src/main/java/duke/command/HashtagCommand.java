package duke.command;

import duke.tag.Tag;
import duke.task.TaskManager;
import duke.ui.Ui;

public class HashtagCommand extends Command {
    private final Tag tag;

    public HashtagCommand(Tag tag) {
        this.tag = tag;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.replyFindTag(taskManager, taskManager.findTaskWithTag(tag));
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
