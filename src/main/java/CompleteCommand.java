public class CompleteCommand extends Command {
    public boolean execute() throws DukeException {
        ui.askTaskNumToComplete();
        int taskNum = Integer.parseInt(sc.nextLine());
        Task task = tm.markDone(taskNum);
        ui.taskCompleted(task);
        return true;
    }
}