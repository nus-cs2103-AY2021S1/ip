package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String tasksString = tasks.findTasks(keyword);
        ui.showAction(String.format("\t Here are the tasks that match %s:\n" + tasksString, keyword));
    }
}