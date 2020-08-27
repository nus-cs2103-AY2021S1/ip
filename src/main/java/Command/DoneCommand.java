package main.java.Command;

import main.java.TaskList;

public class DoneCommand extends Command {

    public DoneCommand() {
        this.commandText = "done";
    }

    @Override
    public void execute(String text, TaskList taskList) {
        int index = Integer.parseInt(text);
        taskList.completeTask(index);
    }
}
