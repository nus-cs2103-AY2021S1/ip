public class ListCommand extends Command {
    public boolean execute() {
        super.tm.listTasks();
        return true;
    }
}