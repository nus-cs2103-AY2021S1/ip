public class CommandDelete implements Command {
    private int n;

    public CommandDelete(int n) {
        this.n = n;
    }

    public void execute(TaskList tasks, Ui ui) throws Exception {
        if (n < 0 || n >= tasks.size()) {
            throw new IllegalDoneArgument();
        }
        Task deleted = tasks.getList().get(n);
        tasks.remove(n);
        ui.printLine("Noted! I've removed this task:");
        ui.printLine(deleted.toString());
        ui.printLine("Now you have " + tasks.size() + " tasks in the list.");
    }

    public boolean isExit() { return false; }
}
