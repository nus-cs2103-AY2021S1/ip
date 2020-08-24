package main.java;

public class ByeCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
    }
}
