public class ListCommand extends Command{
    public ListCommand(String command) {
        super(command, false);

    }

    protected void execute(TaskList list, Ui ui, Storage storage) {
        ui.tasks();
        list.showTasks();
    }
}
