public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList list) {
        ui.printList(list, t -> true, "");
    }
}
