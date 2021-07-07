package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.FileError;
import duke.tasks.Task;

/**
 * Represents a command to tag a task in the task list.
 *
 */
public class TagCommand extends Command {
    private int tagTaskIndex;
    private String tagWord;

    /**
     * Creates TagCommand with task index and tag word to be used.
     *
     * @param taskIndex index to find relevant tasks in the task list.
     * @param tagWord tag word for the task in the list.
     */
    public TagCommand(int taskIndex, String tagWord) {
        this.tagTaskIndex = taskIndex;
        this.tagWord = tagWord;
    }

    /**
     * Executes main logic to tag a task from the task list.
     * Displays task tagged successfully message to user.
     *
     * @param ui Ui used to generate messages to users.
     * @param listStorage Backend storage to store items in the task list.
     * @param taskList List of tasks added by users so far.
     * @return UI message after executing tag command.
     * @throws FileError Unable to process data file.
     */
    @Override
    public String execute(Ui ui, Storage listStorage, TaskList taskList) throws FileError {
        Task taskToBeTagged = taskList.get(this.tagTaskIndex);
        listStorage.editTaggedTask(taskToBeTagged, this.tagTaskIndex, taskList, this.tagWord);
        return ui.taggedTask(taskToBeTagged, this.tagWord);
    }
}
