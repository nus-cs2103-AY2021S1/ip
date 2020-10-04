package duke.command;

import duke.duke.Duke;

public class ByeCommand extends Command {

  public ByeCommand() {
  }

  @Override
  public void execute(Duke duke) throws Exception {
    duke.removeAllObservers();
  }

  @Override
  public void undo(Duke duke) {
    // stub
  }

  @Override
  public boolean isExit() {
    return true;
  }
}
