package duke.operation;

import duke.result.Result;
import duke.task.TaskList;

/**
 * The operation that finds <code>Tasks</code> in <code>TaskList</code> associated with a search word.
 */
public class FindOperation extends Operation {
    private final TaskList taskList;
    private final String searchWord;

    /**
     * Constructor method.
     *
     * @param taskList the <code>TaskList</code> that is to be searched on.
     * @param searchWord the <code>String</code> that <code>TaskList</code> is to be search with.
     */
    public FindOperation(TaskList taskList, String searchWord) {
        this.taskList = taskList;
        this.searchWord = searchWord;
    }

    /**
     * Specifies that this is not an <code>ExitOperation</code>.
     *
     * @return <code>false</code>.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the operation by retrieving all <code>Tasks</code> in
     * <code>TaskList</code> containing the search word.
     *
     * @return a <code>Result</code> containing all <code>Tasks</code> found.
     */
    @Override
    public Result execute() {
        String message;
        String foundTasks = this.taskList.findString(searchWord);
        if (foundTasks.equals("")) {
            message = String.format("I have found no tasks that match: %s", this.searchWord);
        } else {
            message = "Here are the tasks I have found:\n" + foundTasks.toString();
        }
        return new Result(true, message, this.isExit());
    }
}
