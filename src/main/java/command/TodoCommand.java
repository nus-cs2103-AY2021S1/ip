package main.java.command;

import main.java.exception.DescriptionException;
import main.java.parser.Parser;
import main.java.storage.Storage;
import main.java.task.TaskList;
import main.java.task.TodoTask;
import main.java.ui.Ui;

import java.io.IOException;

public class TodoCommand extends Command {

    private String command;

    public TodoCommand(String command) {
        super();
        this.command =command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DescriptionException {
        String taskDescription = Parser.findTodoParser(this.command);
        TodoTask todoTask = new TodoTask(taskDescription);

        tasks.add(todoTask);

        ui.getTaskMessage(todoTask,tasks.size());

        storage.updateFile(tasks);
    }
}
