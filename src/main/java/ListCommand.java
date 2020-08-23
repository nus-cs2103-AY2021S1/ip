public class ListCommand extends Command {
    public void execute (TaskList tasks, Ui ui, Storage storage) throws CustomException {
        Ui.displayTasks(tasks.getList());
    }
}
