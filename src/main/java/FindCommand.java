public class FindCommand extends Command {
    public String commandWord = "find";
    public TaskList taskList;
    public String keyword;

    public FindCommand(TaskList taskList, String keyword) {
        super("list");
        this.taskList = taskList;
        this.keyword = keyword;
    }

    @Override
    public String execute() {
        if (taskList.list.isEmpty()) {
            return Ui.emptyList();
        } else {
            return Ui.returnRelevantTasks(taskList, keyword);
        }
    }
}
