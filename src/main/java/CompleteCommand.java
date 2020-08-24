public class CompleteCommand extends Command {
    public boolean execute() throws DukeException {
        ui.askTaskNumToComplete();
        int taskNum = Integer.parseInt(sc.nextLine());
        tm.markDone(taskNum);
        return true;
    }
}