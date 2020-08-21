public class DeadlineCommand extends Command {

    String command, date;

    DeadlineCommand(String command, String date) {
        this.command = command;
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = new Deadline(command, date);
        taskList.addTask(task);
        ui.printAddTask(taskList);
        storage.save(task);
    }
}
