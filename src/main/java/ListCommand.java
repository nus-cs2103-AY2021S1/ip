package main.java;

public class ListCommand extends Command {

    ListCommand() {
        this.commandText = "list";
    }

    @Override
    public void execute(String text, TaskList taskList) {
        taskList.printStore();
    }
}
