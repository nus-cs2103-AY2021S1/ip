public class ListCommand extends Command {
    ListCommand(TaskList taskList, Ui ui, String arguments) {
        super(taskList, ui, arguments);
    }
    
    @Override
    public String execute() {
        return ui.showCurrentTasks(taskList);
    }
}
