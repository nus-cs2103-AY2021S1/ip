package juke.command.update;

import juke.Storage;
import juke.TaskList;
import juke.task.TaskDate;
/**
 * Represents the command to update a task's date.
 */
public class UpdateDateCommand extends UpdateCommand {

    private TaskDate newDate;

    /**
     * Constructs a UpdateDateCommand.
     * @param index Index position of task to be modified.
     * @param newDate New Date to be used.
     */
    public UpdateDateCommand(int index, TaskDate newDate) {
        super(index);
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
        return taskList.updateDate(super.index, this.newDate);
    }
}
