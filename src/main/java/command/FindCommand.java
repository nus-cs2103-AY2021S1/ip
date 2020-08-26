package command;

import task.Task;
import util.Storage;
import util.TaskList;
import util.Ui;

import java.util.List;

public class FindCommand extends Command{
    private final String query;
    
    public FindCommand(String query) {
        this.query = query;
    }
    
    public void execute(TaskList lst, Ui ui, Storage storage) {
        List<Task> filteredTasks = lst.filter(query);
        for (int i = 0; i < filteredTasks.size(); i++) {
            int num = i + 1;
            ui.showTask(filteredTasks.get(i), num);
        }
        ui.showLine();
    }
}
