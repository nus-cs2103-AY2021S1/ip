package sparrow.commands;

import java.util.ArrayList;

import sparrow.data.task.Task;
import sparrow.data.task.TaskList;
import sparrow.data.trivia.VocabList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_FIND_SUCCESS = "I found tasks matching your search: \n%s";

    public static final String MESSAGE_FIND_FAILURE = "I didn't find any matching tasks :(";

    private final String keyword;

    /**
     * Creates a FindCommand with the given keyword.
     * @param keyword Term to search for.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, VocabList vocabList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        if (matchingTasks.size() == 0) {
            return MESSAGE_FIND_FAILURE;
        } else {
            String matchingTasksAsString = ui.taskListToString(matchingTasks);
            return String.format(MESSAGE_FIND_SUCCESS, matchingTasksAsString);
        }
    }
}
