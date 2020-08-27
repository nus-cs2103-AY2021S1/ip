import java.util.ArrayList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.listTasks();
        //return output.toString();
        ui.printOutput(output, false);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
