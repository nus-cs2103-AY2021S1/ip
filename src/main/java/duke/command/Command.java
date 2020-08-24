package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

/** Command is the base class of all commands */
public abstract class Command {
  private final CommandType commandType;
  private final String commandString;

  public Command(CommandType commandType, String commandString) {
    this.commandType = commandType;
    this.commandString = commandString;
  }

  /**
   * Returns a Command subclass object based on the commandType and commandString given
   *
   * @param commandType of the commandString
   * @param commandString represent the command
   * @return a Command subclass object based on the commandType and commandString given
   * @throws DukeException when the command is invalid
   */
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
      case FIND:
        return new FindCommand(commandString);
      default:
        throw Ui.commandInvalidException();
    }
  }

  /**
   * Returns the description of the task excluding the command type
   *
   * @return the description of the task excluding the command type
   * @throws DukeException when the description is not found
   */
  public String getTaskDescription() throws DukeException {
    return Parser.getTaskDescription(this.commandType, this.commandString);
  }

  /**
   * Returns the command string of the command
   *
   * @return the command string of the command
   */
  public String getCommandString() {
    return commandString;
  }

  /**
   * Returns the command type of the command
   *
   * @return the command type of the command
   */
  public CommandType getCommandType() {
    return this.commandType;
  }

  /**
   * Returns true if and only if the program should exit
   *
   * @return true if and only if the program should exit
   */
  public boolean isExit() {
    return false;
  }

  /**
   * Executes the command
   *
   * @param tasks TaskList object to be modified / accessed by the command
   * @throws DukeException when something goes wrong
   */
  public abstract void execute(TaskList tasks) throws DukeException;
}
