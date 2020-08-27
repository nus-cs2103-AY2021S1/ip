package main.java.Command;

import main.java.TaskList;
import main.java.Ui;

/**
 * Represents command to leave conversation.
 */
public class ByeCommand extends Command {

    public ByeCommand() {
        this.commandText = "bye";
    }

    /**
     * Leaves the conversation after saving the current state of tasks.
     * @param text unused argument.
     * @param taskList current list of tasks to be saved into hard disk.
     */
    @Override
    public void execute(String text, TaskList taskList) {
        taskList.saveIntoHarddisk();
        Ui.showByeMessage();
    }
}
