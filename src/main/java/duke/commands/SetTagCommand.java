package duke.commands;

import duke.exceptions.DukeException;

import duke.task.Task;
import duke.task.TaskManager;
import duke.utils.Messages;

/**
 * Represents an output which will set a tag to the respective {@code Task}.
 */
public class SetTagCommand extends Command {
    private int taskToBeTaggedIndex;
    private String tagName;

    /**
     * Constructor to create a new {@code SetTagCommand} instance containing the task to be tagged index and the
     * tag name.
     *
     * @param taskToBeTaggedIndex the index of the {@code Task} that will be tagged.
     * @param tagName the name of the tag.
     */
    public SetTagCommand(int taskToBeTaggedIndex, String tagName) {
        this.taskToBeTaggedIndex = taskToBeTaggedIndex;
        this.tagName = tagName;
    }

    /**
     * Sets the respective tag to the task of interest and returns an output from setting the tag.
     *
     * @param taskManger the {@code TaskManager} that contains of the list of all {@code Task}.
     * @return the output resulting from setting a tag to the respective {@code Task}.
     * @throws DukeException if the task to be tagged index is out of bounds of the list of {@code Task} in the
     * {@code TaskManager}.
     */
    @Override
    public CommandOutput executeCommand(TaskManager taskManger) throws DukeException {
        try {
            taskManger.setTagToTask(taskToBeTaggedIndex, tagName);
            Task taggedTask = taskManger.getTask(taskToBeTaggedIndex);
            String taggedTaskOutput = outputResult(taggedTask);
            return new CommandOutput(taggedTaskOutput, false);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Messages.INVALID_TASK_INDEX_ERROR_MESSAGE);
        }
    }

    private String outputResult(Task taggedTask) {
        StringBuilder taggedTaskOutput = new StringBuilder("Nice! I have successfully added a tag for this task:\n");
        String taggedTaskDescription = taggedTask.toString();
        taggedTaskOutput.append(taggedTaskDescription);
        return taggedTaskOutput.toString();
    }
}
