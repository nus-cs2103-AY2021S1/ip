package main.java.Command;

import main.java.Exception.AnonymousException;
import main.java.Exception.DescriptionException;
import main.java.Exception.DukeDateTimeParserException;
import main.java.Exception.NoIndexException;
import main.java.Storage.Storage;
import main.java.Task.Task;
import main.java.Task.TaskList;
import main.java.Ui.Ui;

import java.io.IOException;


public class DoneAllCommand extends Command {

    public DoneAllCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        for(Task task : tasks.getTasks()) {
            if(!task.isDone()) {
                task.setStatus(true);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < tasks.size(); i++) {
            if (i != tasks.size()-1) {
                sb.append(ui.formatMessage((i + 1) + ". " +tasks.getTask(i) + "\n"));
            } else {
                sb.append(ui.formatMessage((i + 1) + ". " + tasks.getTask(i)));
            }
        }
        ui.messageTemplate(ui.formatMessage("Here are the tasks in your list:\n" + sb.toString()));

        storage.updateFile(tasks);
    }
}
