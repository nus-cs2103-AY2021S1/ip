package command;
import tasklist.TaskList;
import ui.Ui;
import storage.Storage;

public class ByeCommand extends Command {
    public ByeCommand(){}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String close = "_______________________________________ \n"
                + "Goodbye! See you soon! \n"
                + "_______________________________________ \n";
        System.out.println(close);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
