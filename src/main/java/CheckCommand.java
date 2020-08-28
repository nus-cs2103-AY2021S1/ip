public class CheckCommand extends Command{
    private int checkInt;
    public CheckCommand(int checkInt) {
        this.checkInt = checkInt;
    }

    /**
     * Marks a task as done and prints a success message.
     * txt file and prints a success message.
     * @param ui a Ui instance to enable calling of Ui functions
     * @param storage a Storage instance to enable calling of Storage functions
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        Task task = TaskList.TO_DO_LIST.get(checkInt - 1);
        task.markAsDone();
        storage.save(TaskList.toDoList);
        ui.checkList(task.toString(), task.getTaskStatusIcon());
        ui.printNumberOfTasks(TaskList.TO_DO_LIST.size());
    }
}
