public class ListCommand extends Command {
    public boolean execute() {
        tm.listTasks();
        return true;
    }
}