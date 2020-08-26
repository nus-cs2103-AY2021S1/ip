package main.java.command;

import main.java.parser.Parser;
import main.java.exception.NoIndexException;
import main.java.storage.Storage;
import main.java.task.Task;
import main.java.task.TaskList;
import main.java.ui.Ui;

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

        ui.getMessageTemplate(ui.formatMessage("Noted. I've removed this task:\n")
                + ui.formatMessage(removedTask.toString() + "\n")
                + ui.formatMessage("Now you have " + tasks.size() + " tasks in the list"));

        storage.updateFile(tasks);
    }
}
