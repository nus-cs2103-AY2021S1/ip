package main.java.Command;

import main.java.Parser.Parser;
import main.java.Exception.NoIndexException;
import main.java.Storage.Storage;
import main.java.Task.Task;
import main.java.Task.TaskList;
import main.java.Ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {

    private String command;

    public DeleteCommand(String command) {
        super();
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoIndexException {
        int index = Parser.findIndexParser(this.command);

        Task removedTask = tasks.remove(index-1);

        ui.messageTemplate(ui.formatMessage("Noted. I've removed this task:\n")
                + ui.formatMessage(removedTask.toString() + "\n")
                + ui.formatMessage("Now you have " + tasks.size() + " tasks in the list"));

        storage.updateFile(tasks);
    }
}
