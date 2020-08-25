public class ListCommand extends Command {

    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listTasks(taskList);
    }

    boolean isExit() {
        return false;
    }
}
