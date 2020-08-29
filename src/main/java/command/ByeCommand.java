package main.java.command;

import java.util.List;

import main.java.misc.Ui;
import main.java.task.TaskList;

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
