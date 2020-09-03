import java.util.ArrayList;

public class FindCommand extends Command {
    private String searchString;
    
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }
    
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        int numOfTasks = tasks.size();
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
                output += ui.showTaskWithIndex(i, t) + "\n";
                i++;
            }
        }
        
        return output;
    }

    @Override
    boolean isExit() {
        return false;
    }
}
