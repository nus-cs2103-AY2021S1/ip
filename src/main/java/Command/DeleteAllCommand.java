package main.java.Command;


import main.java.Storage.Storage;
import main.java.Task.TaskList;
import main.java.Ui.Ui;

import java.io.IOException;

public class DeleteAllCommand extends Command {

    public DeleteAllCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {

        int length = tasks.size();
        for(int i = 0 ; i < length; i++) {
            tasks.remove(0);
        }

        ui.messageTemplate(ui.formatMessage("All of your task has been removed!"));

        storage.updateFile(tasks);
    }
}
