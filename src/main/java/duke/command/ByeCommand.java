package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
  public ByeCommand(String commandString) {
    super(CommandType.BYE, commandString);
  }

  @Override
  public void execute(TaskList tasks) {
    Ui.showBye();
  }

  @Override
  public boolean isExit() {
    return true;
  }
}
