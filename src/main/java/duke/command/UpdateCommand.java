package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;

public abstract class UpdateCommand extends Command {
  public UpdateCommand(CommandType commandType, String commandString) {
    super(commandType, commandString);
  }

  public int getTaskTargetIndex() throws DukeException {
    return Parser.getTaskTargetIndex(super.getCommandType(), super.getCommandString());
  }
}
