package juke.command.update;

import java.time.LocalDate;

import juke.Storage;
import juke.TaskList;

public class UpdateDateCommand extends UpdateCommand {

    private LocalDate newDate;

    public UpdateDateCommand(int index, String newDate) {
        super(index);
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
        return taskList.updateDate(super.index, this.newDate);
    }
}
