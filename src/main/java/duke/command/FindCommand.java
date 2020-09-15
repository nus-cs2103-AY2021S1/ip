package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an action to search for Task that contains keyword input by user.
 */
public class FindCommand extends Command {

    /** Keyword to search for */
    private String content;

    /**
     * Constructs a <code>FindCommand</code> object.
     *
     * @param content Keyword to search for.
     */
    public FindCommand(String content) {
        super(false);
        this.content = content;
        assert !content.equals("") : "content should not be empty";
    }

    /**
     * Search for Task in TaskList containing keyword input by user and list them out.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     * @return CommandResponse A response to the user.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = new TaskList();

        for (Task t : tasks.getList()) {
            CharSequence contentToFind = content.subSequence(0, content.length());
            String taskDescription = t.getDescription();
            if (taskDescription.contains(contentToFind)) {
                filteredTasks.addTask(t);
            }
        }

        boolean shouldExit = getIsExit();
        assert !shouldExit : "shouldExit should be false";
        return new CommandResponse(createResponseMessage(filteredTasks), shouldExit);
    }

    private String createResponseMessage(TaskList tasks) {
        if (tasks.getNumberOfTask() == 0) {
            return "You do not have any tasks containing " + "\"" + content + "\"!";
        }
        return tasks.toString().replaceFirst("tasks", "matching tasks");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof FindCommand) {
            FindCommand c = (FindCommand) obj;
            return c.content.equals(this.content) && c.getIsExit() == this.getIsExit();
        } else {
            return false;
        }
    }
}
