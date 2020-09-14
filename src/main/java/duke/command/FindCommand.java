package duke.command;

import duke.main.TaskList;

/**
 * Abstraction for the find command.
 */
public class FindCommand extends TaskListOperator {
    private static final String MESSAGE = "The following task(s) match your search:\n";
    private static final String NO_TASK_FOUND = "Sorry, there are no tasks that "
            + "match your description!";

    private final String keyword;

    /**
     * Constructs a new FindCommand object.
     *
     * @param keyword Keyword to search for.
     * @param taskList TaskList to be operated on.
     */
    public FindCommand(String keyword, TaskList taskList) {
        super(taskList);
        this.keyword = keyword;
    }

    /**
     * Lists all tasks that contain a given keyword in their description.
     *
     * @return List of tasks that match the description, if any.
     */
    @Override
    public String execute() {
        TaskList searchResults = taskList.find(keyword);
        if (searchResults.size() == 0) {
            return NO_TASK_FOUND;
        }

        return MESSAGE + searchResults;
    }
}
