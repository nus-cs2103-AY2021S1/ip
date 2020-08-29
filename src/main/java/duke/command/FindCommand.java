package duke.command;

import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Represents a find command.
 */
public class FindCommand implements Command {
    private String keyword;
    
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds matching tasks by keyword and prints them out in a list.
     * 
     * @param tasks List of user's tasks.
     * @param ui UI for the Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        TaskList matchingTasks = tasks.find(keyword);
        ui.displayMatchingTaskList(matchingTasks);
    }

    /**
     * Tells Duke to keep on keeping on.
     * 
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
