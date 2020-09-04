public class DoneCommand extends Command{

    public DoneCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskManager tm, Ui ui, Storage storage) throws DukeException {
        String[] commandDetails = command.split(" ");
        int doneIndex = Integer.parseInt(commandDetails[1]) - 1;
        Task doneTask = tm.getTask(doneIndex);
        tm.markTaskDone(doneIndex);
        ui.showDetails("Task marked as done: " + doneTask);
    }
}
