import java.time.LocalDate;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final CommandType commandType;
    private final LocalDate date;
    
    public FindCommand(LocalDate date) {
        this.commandType = CommandType.FIND;
        this.date = date;
    }
    
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.findByDate(this.date);
        ui.printFound(date, taskList);
    }
}
