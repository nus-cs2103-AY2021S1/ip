package main.java.command;

import main.java.misc.Ui;
import main.java.exception.InvalidArgumentException;
import main.java.exception.UserException;
import main.java.task.Task;
import main.java.task.TaskList;

import java.util.List;

public class TaskCommand extends Command {
    public TaskCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run(TaskList taskList) throws UserException {
        Task task;
        if (input.size() < 4) throw new InvalidArgumentException("Missing argument(s)");
        task = taskList.addTask(input);
        Ui.task(task.toString(), taskList.count());
    }
}
