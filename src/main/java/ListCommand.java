public class ListCommand extends Command {
    public boolean execute() {
        String s = tm.listTasks();
        ui.print(s);
        return true;
    }
}