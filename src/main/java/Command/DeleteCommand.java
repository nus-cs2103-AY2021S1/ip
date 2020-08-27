package main.java.Command;

import main.java.Command.Command;
import main.java.TaskList;

public class DeleteCommand extends Command {

    public DeleteCommand() {
        this.commandText = "delete";
    }

    @Override
    public void execute(String text, TaskList taskList) {
        int index = Integer.parseInt(text);
        taskList.deleteTask(index);
    }
}
