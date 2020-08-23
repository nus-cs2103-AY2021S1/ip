public abstract class CreateCommand extends Command {
  public CreateCommand(CommandType commandType, String commandString) {
    super(commandType, commandString);
  }

  public void addTask(TaskList tasks, Task task) {
    tasks.add(task);
    Ui.showAddTask(task);
  }
}
