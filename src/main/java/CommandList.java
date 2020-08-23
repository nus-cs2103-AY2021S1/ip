public class CommandList implements Command {
    public void execute(TaskList tasks, Ui ui) { ui.printList(tasks.getList()); }
    public boolean isExit() { return false; }
}
