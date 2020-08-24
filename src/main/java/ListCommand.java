public class ListCommand extends Command {

    protected ListCommand(){}

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getTasks());
    }
}
