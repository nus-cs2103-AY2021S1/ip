package duke;


public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) {
        ui.display(tasks.toDisplayString());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
