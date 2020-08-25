package main.java.command;

import main.java.misc.Ui;
import main.java.exception.InvalidArgumentException;
import main.java.task.TaskList;

import java.util.List;

public class DoneCommand extends Command {
    public DoneCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run(TaskList taskList) throws InvalidArgumentException {
        int index = Integer.parseInt(input.get(1));
        taskList.finishTask(index);
        Ui.done(taskList.getTask(index).toString());
    }
}
