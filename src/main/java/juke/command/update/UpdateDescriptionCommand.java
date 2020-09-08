package juke.command.update;

import juke.Storage;
import juke.TaskList;

/**
 * Represents the command to update a task's description.
 */
public class UpdateDescriptionCommand extends UpdateCommand {

    private String newDescription;

    /**
     * Constructs an UpdateDescriptionCommand.
     * @param index Index position of task.
     * @param newDescription New description to be used.
     */
    public UpdateDescriptionCommand(int index, String newDescription) {
        super(index);
        this.newDescription = newDescription;
    }

    /**
     * Executes whats required based on the type of command.
     *
     * @param taskList List of tasks
     * @param storage  Storage of tasks onto disk
     * @return Response Text to be output by chatbot.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return taskList.updateDescription(super.index, this.newDescription);
    }
}
