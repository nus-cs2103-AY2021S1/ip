package main.java.command;


import main.java.storage.Storage;
import main.java.task.TaskList;
import main.java.ui.Ui;

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

        ui.getMessageTemplate(ui.formatMessage("All of your task has been removed!"));

        storage.updateFile(tasks);
    }
}
