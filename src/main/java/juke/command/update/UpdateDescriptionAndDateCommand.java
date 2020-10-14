package juke.command.update;

import juke.Storage;
import juke.TaskList;
import juke.task.TaskDate;

/**
 * Represents the command to update both the task description and date.
 */
public class UpdateDescriptionAndDateCommand extends UpdateCommand {

    private String newDescription;
    private TaskDate newDate;

    /**
     * Constructs a UpdateDescriptionAndDateCommand.
     * @param index Index position of Task.
     * @param newDescription New Description to be used.
     * @param newDate New Date to be used.
     */
    public UpdateDescriptionAndDateCommand(int index, String newDescription, TaskDate newDate) {
        super(index);
        this.newDescription = newDescription;
        this.newDate = newDate;
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
        return taskList.updateDescriptionAndDate(super.index, this.newDescription, this.newDate);
    }
}
