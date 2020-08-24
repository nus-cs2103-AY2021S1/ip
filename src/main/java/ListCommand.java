public class ListCommand extends Command{
    public ListCommand() {
        super();
    }

    @Override
    public void execute(Ui ui, Storage listStorage, TaskList taskList) {
        ui.listItems(taskList);
    }
}
