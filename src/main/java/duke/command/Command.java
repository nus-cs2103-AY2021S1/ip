package duke.command;

import duke.duke.Duke;

public abstract class Command {
  public abstract void execute(Duke duke) throws Exception;
  public abstract void undo(Duke duke);
  public abstract boolean isExit();
}
