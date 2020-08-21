package main.java;

public class ExitCommand extends Command {

    public ExitCommand(String[] splitCommand) {
        super(splitCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    public boolean isExit() {
        return true;
    }
}
