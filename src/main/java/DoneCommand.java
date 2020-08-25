package main.java;

import java.io.IOException;

public class DoneCommand extends Command {
    int index;

    DoneCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws BobIOException, BobIndexOutOfBoundsException {
        tasks.get(index).markAsDone();
        ui.markAsDone(tasks,index);
        storage.updateSave(tasks);
    }
}
