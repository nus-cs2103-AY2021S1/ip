package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.LinkedList;

/**
 * The command to find tasks with search keyword.
 */
public class CommandFind implements Command {
    private final String keyword;

    /**
     * Construct a new find command with search keyword.
     * @param keyword the string to search
     */
    public CommandFind(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Execute the command to search all tasks with keyword.
     * @param tasks the <code>TaskList</code> to operate on
     * @param ui the <code>Ui</code> object to handle interface
     */
    public void execute(TaskList tasks, Ui ui) {
        LinkedList<Task> results = new LinkedList<Task>();
        for (Task result : tasks.getList()) {
            if (result.getDescription().contains(keyword)) {
                results.add(result);
            }
        }
        if (results.size() == 0) {
            ui.printLine("No tasks containing " + keyword + " found!");
        } else {
            ui.printLine("Found " + results.size() + " tasks:");
            ui.printList(results);
        }
    }

    /**
     * Return <code>false</code> since the command is not exit.
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
