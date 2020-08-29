package main.java.command;

import java.util.List;

import main.java.exception.InvalidArgumentException;
import main.java.misc.Ui;
import main.java.task.Task;
import main.java.task.TaskList;


public class DeleteCommand extends Command {
    public DeleteCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run(TaskList taskList) throws InvalidArgumentException {
        int index = Integer.parseInt(input.get(1));
        Task task = taskList.removeTask(index);
        Ui.delete(task.toString(), taskList.count());
    }
}
