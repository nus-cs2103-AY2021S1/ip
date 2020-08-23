import java.io.IOException;

public class ExitCommand extends Command {
    public void execute (TaskList tasks, Ui ui, Storage storage) throws CustomException, IOException {
        Storage.writeToFile(tasks.getList());
        Ui.display("Bye! Hope to see you again! :D");
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
