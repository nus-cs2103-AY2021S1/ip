public class PrintlistCommand extends Command{
    @Override
    public void execute(Ui ui, Storage storage) {
        ui.displayList(TaskList.TO_DO_LIST);
        ui.printNumberOfTasks(TaskList.TO_DO_LIST.size());
    }
}
