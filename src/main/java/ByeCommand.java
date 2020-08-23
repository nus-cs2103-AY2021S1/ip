package main.java;

import java.util.ArrayList;

public class ByeCommand extends Command {

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
