public class DeleteCommand extends Command{

    public DeleteCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskManager tm, Ui ui, Storage storage) throws DukeException {
        String[] commandDetails = command.split(" ");
        int delIndex = Integer.parseInt(commandDetails[1]) - 1;
        Task delTask = tm.getTask(delIndex);
        tm.deleteTask(delIndex);
        ui.showDetails("Task deleted: " + delTask);
    }
}
