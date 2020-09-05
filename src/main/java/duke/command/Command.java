package duke.command;

import duke.duke.Duke;

public abstract class Command {

  public abstract void execute(Duke duke);
  public abstract void undo(Duke duke);
}
