package main.java.command;

import java.util.List;

import main.java.misc.Ui;
import main.java.task.TaskList;


public class FindCommand extends Command {
    public FindCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run(TaskList taskList) {
        Ui.find(taskList.findTasks(input.get(1)));
    }
}
