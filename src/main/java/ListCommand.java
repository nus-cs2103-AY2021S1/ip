public class ListCommand extends Command{

    private Ui ui;
    private TaskList taskList;

    public ListCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }
    @Override
    public void execute() {
        this.ui.showTaskList(this.taskList.getTaskList());
    }
}
