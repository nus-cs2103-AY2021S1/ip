package main.java;

public class ExitCommand extends Command {
    @Override
    void excecute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isContinuing() {
        return false;
    }
}
