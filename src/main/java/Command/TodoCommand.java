package main.java.Command;

import main.java.DescriptionException;
import main.java.Parser;
import main.java.Storage;
import main.java.TaskList;
import main.java.TodoTask;
import main.java.Ui;

import java.io.IOException;

public class TodoCommand extends Command {

    private String command;

    public TodoCommand(String command) {
        super();
        this.command =command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DescriptionException {
        String taskDescription = Parser.todoParser(this.command);
        TodoTask todoTask = new TodoTask(taskDescription);

        tasks.add(todoTask);

        ui.taskMessage(todoTask,tasks.size());

        storage.updateFile(tasks);
    }
}
