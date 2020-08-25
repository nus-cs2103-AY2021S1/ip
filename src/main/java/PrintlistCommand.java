public class PrintlistCommand extends Command{
    @Override
    public void execute(Ui ui, Storage storage) {
        ui.displayList(TaskList.toDoList);
        ui.printNumberOfTasks(TaskList.toDoList.size());
    }
}
