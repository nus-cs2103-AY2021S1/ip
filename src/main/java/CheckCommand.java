public class CheckCommand extends Command{
    int checkInt;
    public CheckCommand(int checkInt) {
        this.checkInt = checkInt;
    }
    @Override
    public void execute(Ui ui, Storage storage) {
        Task task = TaskList.TO_DO_LIST.get(checkInt - 1);
        task.markAsDone();
        ui.checkList(task.toString(), task.getTaskStatusIcon());
        ui.printNumberOfTasks(TaskList.TO_DO_LIST.size());
    }
}
