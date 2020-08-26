package main.java;

public class ByeCommand extends Command {

    ByeCommand() {
        this.commandText = "bye";
    }

    @Override
    public void execute(String text, TaskList taskList) {
        taskList.saveIntoHarddisk();
        Ui.showByeMessage();
    }
}
