package command;

import java.io.IOException;

import task.Task;
import util.Storage;
import util.Tag;
import util.TaskList;
import util.Ui;


/**
 * Represents the tag command. The tag command creates a new tag and tags it to an existing task.
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
     * Creates a new Tag command.
     *
     * @param taskNum Task number of task to be tagged.
     */
    public TagCommand(int taskNum, String tagName) {
        this.taskNum = taskNum;
        this.tagName = tagName;
    }

    /**
     * Executes the tag command. The execution involves tagging the task with the newly created tag,
     * writing to the storage as well as printing the relevant UI.
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
        try {
            storage.modifyLine(lineNum, tag);
            task.setTag(tag);
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }
        return ui.showTagAdded(task, taskNum);
    }
}
