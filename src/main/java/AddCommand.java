public class AddCommand extends Command{
    private Task myTask;
    public AddCommand(Task myTask) {
        this.myTask = myTask;
    }

    /**
     * Adds a task to toDoList in TaskList, saves the new task list in a
     * txt file and prints a success message.
     * @param ui a Ui instance to enable calling of Ui functions
     * @param storage a Storage instance to enable calling of Storage functions
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        TaskList.addToList(myTask);
        storage.save(TaskList.toDoList);
        ui.printFormat(myTask.toString());
        ui.printNumberOfTasks(TaskList.toDoList.size());
    }
}
