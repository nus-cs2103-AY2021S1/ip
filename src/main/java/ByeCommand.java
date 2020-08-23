package main.java;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
