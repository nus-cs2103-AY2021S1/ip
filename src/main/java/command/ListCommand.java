package main.java.command;

import java.util.List;

import main.java.misc.Ui;
import main.java.task.TaskList;


public class ListCommand extends Command {
    public ListCommand(List<String> input) {
        super(input);
    }

    @Override
    public void run(TaskList taskList) {
        Ui.list(taskList.printTasks());
    }
}
