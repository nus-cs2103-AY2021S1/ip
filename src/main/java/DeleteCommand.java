public class DeleteCommand extends Command {
    public boolean execute() throws DukeException {
        ui.askTaskNumToDelete();
        int taskNum = Integer.parseInt(sc.nextLine());
        tm.deleteTask(taskNum);
        return true;
    }
}