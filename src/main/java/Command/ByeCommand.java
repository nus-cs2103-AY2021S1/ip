package main.java.Command;

import main.java.TaskList;
import main.java.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {
        this.commandText = "bye";
    }

    @Override
    public void execute(String text, TaskList taskList) {
        taskList.saveIntoHarddisk();
        Ui.showByeMessage();
    }
}
