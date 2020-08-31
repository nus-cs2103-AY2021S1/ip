package sparrow.commands;

import sparrow.data.task.Task;
import sparrow.data.task.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_FIND_SUCCESS = "I found tasks matching your search: \n%s";

    public static final String MESSAGE_FIND_FAILURE = "I didn't find any matching tasks :(";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        if (matchingTasks.size() == 0) {
            ui.replyToUser(MESSAGE_FIND_FAILURE);
        } else {
            String matchingTasksAsString = ui.taskListToString(matchingTasks);
            ui.replyToUser(String.format(MESSAGE_FIND_SUCCESS, matchingTasksAsString));
        }
    }
}
