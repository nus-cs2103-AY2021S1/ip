package main.java.command;

import java.util.List;

import main.java.misc.Ui;
import main.java.task.TaskList;


public class ClearCommand extends Command {
    public ClearCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run(TaskList taskList) {
        taskList.clearAll();
        Ui.clear();
    }
}
