package juke.command.update;

import juke.Storage;
import juke.TaskList;

import java.time.LocalDate;

public class UpdateDescriptionAndDateCommand extends UpdateCommand {

    private String newDescription;
    private LocalDate newDate;

    public UpdateDescriptionAndDateCommand(int index, String newDescription, String newDate) {
        super(index);
        this.newDescription = newDescription;
        this.newDate = LocalDate.parse(newDate);
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
