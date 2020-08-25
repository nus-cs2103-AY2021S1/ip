package main.java;

import java.io.IOException;

public class DeleteCommand extends Command {

    int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws BobIOException, BobIndexOutOfBoundsException {
        ui.deleteTask(tasks,index);
        tasks.delete(index);
        storage.updateSave(tasks);
    }

}
