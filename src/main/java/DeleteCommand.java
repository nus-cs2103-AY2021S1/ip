public class DeleteCommand extends UpdateCommand {
  public DeleteCommand(String commandString) {
    super(CommandType.DELETE, commandString);
  }

  @Override
  public void execute(TaskList tasks) throws DukeException {
    int index = super.getTaskTargetIndex();
    Task task = tasks.remove(index);
    Ui.showDelete(task);
  }
}
