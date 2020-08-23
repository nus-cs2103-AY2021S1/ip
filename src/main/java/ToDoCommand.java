public class ToDoCommand extends CreateCommand {
  public ToDoCommand(String commandString) {
    super(CommandType.TODO, commandString);
  }

  @Override
  public void execute(TaskList tasks) throws DukeException {
    String description = this.getTaskDescription();
    this.addTask(tasks, new ToDo(description));
  }
}
