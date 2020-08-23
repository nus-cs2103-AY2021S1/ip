public class EventCommand extends CreateCommand {
  public EventCommand(String commandString) {
    super(CommandType.EVENT, commandString);
  }

  public void execute(TaskList tasks) throws DukeException {
    String description = super.getTaskDescription();
    String[] taskParts = description.split(" /at ", 2);

    if (taskParts.length != 2 || taskParts[1].equals("")) {
      throw Ui.taskDateEmptyException(super.getCommandType());
    }

    String name = taskParts[0];
    String dateString = taskParts[1];
    super.addTask(tasks, new Event(name, Parser.parseDateString(dateString)));
  }
}
