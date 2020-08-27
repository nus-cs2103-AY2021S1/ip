public class FindCommand extends Command {
    private String searchString;
    
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }
    
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        int numOfTasks = tasks.size();
        for (int i = 1; i <= numOfTasks; i++) {
            Task currTask = tasks.getTask(i);
            String currDesc = currTask.getDesc();
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
