public class TodoCommand extends Command{

    String command;

    public TodoCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(new Todo(command));
        ui.printAddTask(taskList);
    }
}
