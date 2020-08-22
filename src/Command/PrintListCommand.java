package Command;


import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

public class PrintListCommand extends Command {

    public PrintListCommand(String[] command) {
        super(command);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
