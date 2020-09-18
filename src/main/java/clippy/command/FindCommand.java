package clippy.command;

import clippy.storage.Storage;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String searchString;
    
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int numOfTasks = tasks.size();
        assert numOfTasks >= 0 : "Negative number of tasks in Tasklist";
        ArrayList<Task> matchedTasks = new ArrayList<>();
        String output = "";
        
        for (int i = 1; i <= numOfTasks; i++) {
            Task currTask = tasks.getTask(i);
            String currDesc = currTask.getDesc();
            if (currDesc.contains(searchString)) {
                matchedTasks.add(currTask);
            }
        }
        
        if (matchedTasks.isEmpty()) {
            output = ui.showNoMatch();
        } else {
            output += ui.showMatchingTaskHeader() + "\n";
            int i = 1;
            for (Task t : matchedTasks) {
                output += ui.showTaskWithIndex(i, t) 
                        + " at List Index: " + t.getTaskIndexInList() + "\n";
                i++;
            }
        }
        
        return output;
    }

}
