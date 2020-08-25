package duke;

public class ExitCommand extends Command {
    public ExitCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    @Override
    void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        System.out.println("Sayonara! See you again my friend!");
    }

    @Override
    boolean isExitProgram() {
        return true;
    }
}
