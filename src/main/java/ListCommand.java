package main.java;

import java.util.ArrayList;

public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
