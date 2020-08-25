package duke;

public class ClearCommand extends Command{
    public ClearCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    @Override
    void execute(TaskList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        tasks.clearList();
        System.out.println("All your tasks have been removed!\n");
    }
}
