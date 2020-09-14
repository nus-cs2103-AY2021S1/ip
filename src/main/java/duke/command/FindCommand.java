package duke;

import duke.command.Command;

/**
 * <p>duke.FindCommand class defines the behaviour of a command to find an item in the task list.</p>
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.replyFind(taskManager, taskManager.findTaskThatHasKeyword(keyword));
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
