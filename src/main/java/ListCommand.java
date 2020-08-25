public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayList(tasks.getList());
    }
}
