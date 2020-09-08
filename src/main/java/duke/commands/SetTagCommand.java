package duke.commands;

import duke.exceptions.DukeException;

import duke.task.Task;
import duke.task.TaskManager;
import duke.utils.Messages;

public class SetTagCommand extends Command {
    private int taskToBeTaggedIndex;
    private String tagName;

    public SetTagCommand(int taskToBeTaggedIndex, String tagName) {
        this.taskToBeTaggedIndex = taskToBeTaggedIndex;
        this.tagName = tagName;
    }

    @Override
    public CommandOutput executeCommand(TaskManager taskManger) throws DukeException {
        try {
            taskManger.setTagToTask(taskToBeTaggedIndex, tagName);
            Task taggedTask = taskManger.getTask(taskToBeTaggedIndex - 1);
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
