public class EventCommand extends Command {

    String command, date;

    public EventCommand(String command, String date) {
        this.command = command;
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = new Event(command, date);
        taskList.addTask(task);
        ui.printAddTask(taskList);
        storage.save(task);
    }
}
