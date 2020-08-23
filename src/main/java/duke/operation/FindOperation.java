package duke.operation;

import duke.task.TaskList;
/**
 * The operation that finds Tasks in TaskList associated with a search word.
 */
public class FindOperation extends Operation {
    private final TaskList taskList;
    private final String searchWord;

    /**
     * Constructor method.
     * @param taskList the TaskList that is to be searched on.
     * @param searchWord the String that TaskList is to be search with.
     */
    public FindOperation(TaskList taskList, String searchWord) {
        this.taskList = taskList;
        this.searchWord = searchWord;
    }

    /**
     * Specifies that this is not an ExitOperation.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the Find Operation by retrieving all Tasks in TaskList containing the search word.
     * @return a String containing all Tasks found.
     */
    @Override
    public String execute() {
        TaskList foundTasks = this.taskList.findString(searchWord);
        if (foundTasks.getCurrCapacity() == 0) {
            return String.format("I have found no tasks that match: %s", this.searchWord);
        }
        return "Here are the tasks I have found:\n" + foundTasks.toString();
    }
}
