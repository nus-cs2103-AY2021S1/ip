package main.java.command;

import main.java.exception.UserException;
import main.java.misc.Ui;
import main.java.task.TaskList;

import java.util.List;

public class FindCommand extends Command {
    public FindCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run(TaskList taskList) {
        Ui.find(taskList.findTasks(input.get(1)));
    }
}
