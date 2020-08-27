package main.java.Command;

import main.java.TaskList;

public class FindCommand extends Command{

    public FindCommand() {
        this.commandText = "find";
    }

    @Override
    public void execute(String text, TaskList taskList) {
        taskList.matchTasks(text);
    }
}