public class AddCommand extends Command{
    public Task myTask;
    public AddCommand(Task myTask) {
        this.myTask = myTask;
    }

    @Override
    public void execute(Ui ui, Storage storage) {
        TaskList.addToList(myTask);
        storage.save(TaskList.TO_DO_LIST);
        ui.printFormat(myTask.toString());
        ui.printNumberOfTasks(TaskList.TO_DO_LIST.size());
    }
}
