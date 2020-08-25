package main.java.command;

import main.java.misc.Ui;
import main.java.task.TaskList;

import java.util.List;

public class ByeCommand extends Command {
    public ByeCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run(TaskList taskList) {
        taskList.save();
        Ui.bye();
    }
}
