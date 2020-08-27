import java.util.ArrayList;

public class FindCommand extends Command {
    private String searchString;
    
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }
    
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        int numOfTasks = tasks.size();
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (int i = 1; i <= numOfTasks; i++) {
            Task currTask = tasks.getTask(i);
            String currDesc = currTask.getDesc();
            if (currDesc.contains(searchString)) {
                matchedTasks.add(currTask);
            }
        }
        if (matchedTasks.isEmpty()) {
            ui.showNoMatch();
        } else {
            ui.showMatchingTaskHeader();
            int i = 1;
            for (Task t : matchedTasks) {
                ui.showTaskWithIndex(i, t);
                i++;
            }
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
