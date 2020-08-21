

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList);
    }
}
