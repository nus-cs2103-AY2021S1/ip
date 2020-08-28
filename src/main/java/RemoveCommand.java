public class RemoveCommand extends Command {
    public int removeInt;

    public RemoveCommand(int removeInt) {
        this.removeInt = removeInt;
    }

    @Override
    public void execute(Ui ui, Storage storage) {
        ui.removePrint(removeInt);
        TaskList.removeFromList(removeInt);
        storage.save(TaskList.TO_DO_LIST);
        ui.printNumberOfTasks(TaskList.TO_DO_LIST.size());
    }
}
