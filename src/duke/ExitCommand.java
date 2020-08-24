package duke;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        processBye(ui);
    }

    public void processBye(Ui ui) {
        ui.bye();
    }

}
