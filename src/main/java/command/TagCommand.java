package command;

import java.io.IOException;

import task.Task;
import util.Storage;
import util.Tag;
import util.TaskList;
import util.Ui;


/**
 * Represents the tag command. Firstly, the tag command can create a new tag and tag it to an existing task.
 * Secondly, if the task has an existing tag, the tag command replaces the existing tag with the newly created tag.
 * Lastly, the tag command can also delete a tag from a task.
 */
public class TagCommand extends Command {
    /**
     * Integer representing the task number to be marked as done.
     */
    private final int taskNum;

    /**
     * Name of tag to be tagged to task.
     */
    private final String tagName;

    /**
     * Boolean representing if the tag command should be one that deletes a tag.
     */
    private final boolean isDelete;

    /**
     * Creates a new Tag command.
     *
     * @param taskNum Task number of task to be tagged.
     */
    public TagCommand(int taskNum, String tagName, boolean isDelete) {
        this.taskNum = taskNum;
        this.tagName = tagName;
        this.isDelete = isDelete;
    }

    /**
     * Executes the tag command. The execution involves tagging the task with the newly created tag, replacing an
     * existing tag, or deleting the tag from the task. It also involves writing to the storage and printing the
     * relevant UI.
     *
     * @param lst     List containing the current tasks.
     * @param ui      Ui allows execute to carry out ui methods to print to the console.
     * @param storage Storage allows execute to write and read files.
     * @return String response by the application after executing the command.
     */
    public String execute(TaskList lst, Ui ui, Storage storage) {
        int lineNum = taskNum - 1;
        Task task = lst.get(lineNum);
        Tag tag = new Tag(tagName);
        boolean isTagged = task.isTagged();
        if (isDelete) {
            task.removeTag();
        } else {
            task.setTag(tag);
        }
        try {
            storage.modifyLineTag(lineNum, tag, isTagged, isDelete);
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }
        return isDelete ? ui.showTagRemoved(task, taskNum) : ui.showTagAdded(task, taskNum);
    }
}
