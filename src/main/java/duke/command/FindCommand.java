package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an action to search for Task that contains keywords input by user.
 */
public class FindCommand extends Command {

    /** Keywords to search for */
    private final String content;

    /**
     * Constructs a <code>FindCommand</code> object.
     *
     * @param content Keywords to search for.
     */
    public FindCommand(String content) {
        super(false);
        this.content = content;
        assert !content.equals("") : "content should not be empty";
    }

    /**
     * Search for Task in TaskList containing keywords input by user and list them out.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) {
        String responseMessage = "";
        StringBuffer sb = new StringBuffer();
        int index = 1;
        sb.append("Here are the matching tasks in your list:\n\t ");
        boolean hasTasks = false;
        for (Task t : tasks.getList()) {
            CharSequence contentToFind = content.subSequence(0, content.length());
            String taskDescription = t.getDescription();
            if (taskDescription.contains(contentToFind)) {
                hasTasks = true;
                sb.append(index).append(".").append(t.toString()).append("\n\t ");
                index++;
            }
        }
        if (hasTasks) {
            responseMessage = sb.delete(sb.length() - 3, sb.length() - 1).toString();
        } else {
            responseMessage = "You do not have any tasks containing " + "\"" + content + "\"!";
        }
        boolean shouldExit = getIsExit();
        assert !shouldExit : "shouldExit should be false";
        return new CommandResponse(responseMessage, shouldExit);
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
