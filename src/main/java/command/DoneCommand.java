package main.java.command;

import main.java.parser.Parser;
import main.java.exception.NoIndexException;
import main.java.storage.Storage;
import main.java.task.Task;
import main.java.task.TaskList;
import main.java.ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command {

    private String command;

    public DoneCommand(String command) {
        super();
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoIndexException {
        int index = Parser.findIndexParser(this.command);
        Task task = tasks.getTask(index-1);
        task.setDone(true);

        ui.getMessageTemplate(ui.formatMessage("Nice! I've marked this task as done:\n"
                +ui.formatMessage(task.toString())));

        storage.updateFile(tasks);

    }
}
