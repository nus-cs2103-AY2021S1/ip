package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.Tasks;

import java.time.LocalDate;

import java.util.ArrayList;

public class FindDateCommand extends FindCommand {
    private final CommandType commandType;
    private final LocalDate date;
    
    public FindDateCommand(LocalDate date) {
        this.commandType = CommandType.FIND;
        this.findCommandType = FindCommandType.DATE;
        this.date = date;
    }
    
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.findByDate(this.date);
        ui.printFound(date, taskList);
    }
}
