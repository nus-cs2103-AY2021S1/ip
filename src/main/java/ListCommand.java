public class ListCommand extends Command {

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showTaskList(taskList.toString());
    }
}
