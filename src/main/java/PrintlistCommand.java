public class PrintlistCommand extends Command{

    /**
     * Prints out all the tasks in the task list.
     * @param ui a Ui instance to enable calling of Ui functions
     * @param storage a Storage instance to enable calling of Storage functions
     */
    @Override
    public void execute(Ui ui, Storage storage) {

        ui.displayList(TaskList.TO_DO_LIST, "Check out your missions!");
        ui.printNumberOfTasks(TaskList.TO_DO_LIST.size());
    }
}
