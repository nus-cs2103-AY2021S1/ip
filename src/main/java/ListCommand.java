public class ListCommand extends Command {

    public ListCommand() {
        this.exit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.getList());
    }
}
