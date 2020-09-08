package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

import java.io.IOException;

public class RescheduleCommand {
    private String newDateAndTime;
    private int indexOfTaskToReschedule;
    public RescheduleCommand(int indexOfTaskToReschedule, String newDateAndTime) {
        this.newDateAndTime = newDateAndTime;
        this.indexOfTaskToReschedule = indexOfTaskToReschedule;
    }

//    @Override
//    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
//        return new CommandResult("String here");
//    }
}
