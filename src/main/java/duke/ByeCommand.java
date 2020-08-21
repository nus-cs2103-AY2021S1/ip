package duke;

public class ByeCommand extends Command {
    public ByeCommand(String input) {
        super(input);
        isExit = true;
    }

    @Override
    public void execute(Ui ui, TaskList list, Storage storage) {
        ui.printList(list, t -> true, "");
    }
}
