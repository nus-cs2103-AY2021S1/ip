import java.util.Arrays;
import java.util.List;

public class ExitCommand implements Command {

    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }
}
