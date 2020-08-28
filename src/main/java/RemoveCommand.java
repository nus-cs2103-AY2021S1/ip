public class RemoveCommand extends Command {
    public int removeInt;

    public RemoveCommand(int removeInt) {
        this.removeInt = removeInt;
    }

    /**
     * Removes a task from the task list, saves the changes to a txt file
     * and prints a success message.
     * @param ui a Ui instance to enable calling of Ui functions
     * @param storage a Storage instance to enable calling of Storage functions
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        ui.removePrint(removeInt);
        TaskList.removeFromList(removeInt);
        storage.save(TaskList.TO_DO_LIST);
        ui.printNumberOfTasks(TaskList.TO_DO_LIST.size());
    }
}
