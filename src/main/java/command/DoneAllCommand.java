package main.java.command;

import main.java.storage.Storage;
import main.java.task.Task;
import main.java.task.TaskList;
import main.java.ui.Ui;

import java.io.IOException;


public class DoneAllCommand extends Command {

    public DoneAllCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        for(Task task : tasks.getTasks()) {
            if(!task.isDone()) {
                task.setDone(true);
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
        ui.getMessageTemplate(ui.formatMessage("Here are the tasks in your list:\n" + sb.toString()));

        storage.updateFile(tasks);
    }
}
