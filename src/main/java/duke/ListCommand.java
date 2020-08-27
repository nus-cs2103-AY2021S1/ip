package main.java.duke;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(tasks.listAllTasks());
    }
}
