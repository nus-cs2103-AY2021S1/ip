package main.java.Command;

import main.java.Command.Command;
import main.java.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
        this.commandText = "list";
    }

    @Override
    public void execute(String text, TaskList taskList) {
        taskList.printStore();
    }
}
