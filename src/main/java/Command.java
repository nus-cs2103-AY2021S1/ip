public abstract class Command {
  private final CommandType commandType;
  private final String commandString;

  public Command(CommandType commandType, String commandString) {
    this.commandType = commandType;
    this.commandString = commandString;
  }

  public static Command create(CommandType commandType, String commandString) throws DukeException {
    switch (commandType) {
      case LIST_DATE:
        return new ListDateCommand(commandString);
      case LIST:
        return new ListCommand(commandString);
      case DONE:
        return new DoneCommand(commandString);
      case TODO:
        return new ToDoCommand(commandString);
      case DEADLINE:
        return new DeadlineCommand(commandString);
      case EVENT:
        return new EventCommand(commandString);
      case DELETE:
        return new DeleteCommand(commandString);
      case BYE:
        return new ByeCommand(commandString);
      default:
        throw Ui.commandInvalidException();
    }
  }

  public String getTaskDescription() throws DukeException {
    return Parser.getTaskDescription(this.commandType, this.commandString);
  }

  public String getCommandString() {
    return commandString;
  }

  public CommandType getCommandType() {
    return this.commandType;
  }

  public boolean isExit() {
    return false;
  }

  public abstract void execute(TaskList tasks) throws DukeException;
}
