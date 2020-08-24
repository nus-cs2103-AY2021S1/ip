package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/** ByeCommand signals the program should end */
public class ByeCommand extends Command {
  public ByeCommand(String commandString) {
    super(CommandType.BYE, commandString);
  }

  /** Prints goodbye output */
  @Override
  public void execute(TaskList tasks) {
    Ui.showBye();
  }

  @Override
  public boolean isExit() {
    return true;
  }
}
