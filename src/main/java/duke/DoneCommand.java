package duke;

public class DoneCommand implements Command {
    private Storage store;

    /**
     * Update task status in store
     * @param store
     */
    public DoneCommand(Storage store) {
        this.store = store;
    }

    public String execute() {
        return "Done";
    }
}
