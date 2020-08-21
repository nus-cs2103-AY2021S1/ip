public class ByeCommand extends Command {
    public ByeCommand() {
        isExit = true;
    }

    @Override
    public void execute(Ui ui, TaskList list) {
        ui.printList(list, t -> true, "");
    }
}
