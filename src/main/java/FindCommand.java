public class FindCommand extends Command {
    
    private final String s;
    
    FindCommand(String s) {
        this.s = s;
    }
    
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        String matchingTasks = taskList.findTask(s);
        ui.showMatchingTask(matchingTasks);
    }
}
