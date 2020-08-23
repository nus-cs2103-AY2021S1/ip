public class CommandDone implements Command {
    private int n;

    public CommandDone(int n) {
        this.n = n;
    }

    public void execute(TaskList tasks, Ui ui) throws Exception {
        if (n < 0 || n >= tasks.size()) {
            throw new IllegalDoneArgument();
        }
        tasks.markAsDone(n);
        ui.printLine("Nice! I've marked this task as done:");
        ui.printLine(tasks.getList().get(n).toString());
    }

    public boolean isExit() { return false; }
}
