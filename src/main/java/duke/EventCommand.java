package duke;

public class EventCommand implements Command {
    Task toBeAdded;

    public EventCommand(Task toBeAdded) {
        this.toBeAdded = toBeAdded;
    }

    @Override
    public void execute(TaskList tasks) {
        // Do TaskList stuff
        tasks.add(toBeAdded);
        // Do UI stuff
        Ui.printAdd(toBeAdded.getDescription() + "\n", tasks.length());
        // Do storage stuff
        // tbc
    }
}
