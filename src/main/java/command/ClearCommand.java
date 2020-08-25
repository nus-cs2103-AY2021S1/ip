package main.java.command;

import main.java.misc.Ui;
import main.java.task.TaskList;

import java.util.List;

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
