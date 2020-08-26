public class ListCommand extends Command {

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList.toString());
    }
}
