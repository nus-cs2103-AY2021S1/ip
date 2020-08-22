package main.java;

public class ByeCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String byeMessage = "\t Bye. Hope to see you again soon!\n";
        ui.showMessage(byeMessage);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
