public class FindCommand extends Command {
    
    private final String keyword;
    
    FindCommand(String keyword) {
        this.keyword = keyword;
    }
    
    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) {
        String matchingTasks = taskList.findTask(keyword);
        return ui.showMatchingTask(matchingTasks);
    }
}
