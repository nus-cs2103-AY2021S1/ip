public class DisplayListCommand implements Command {

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.displayList(taskList.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
