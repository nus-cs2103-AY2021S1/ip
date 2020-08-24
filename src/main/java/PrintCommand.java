public class PrintCommand extends Command{

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(storage);
    }
    @Override
    boolean isExit() {
        return false;
    }
}
