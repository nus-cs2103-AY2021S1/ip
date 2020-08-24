import java.io.IOException;

public class PrintCommand extends Command{

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.printList(storage);
    }
    @Override
    boolean isExit() {
        return false;
    }
}
