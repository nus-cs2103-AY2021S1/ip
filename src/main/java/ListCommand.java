public class ListCommand implements Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showLine();
        ui.printTasks(taskList.tasks);
        ui.showLine();
    }

    public boolean isExit() {
        return false;
    }
}
