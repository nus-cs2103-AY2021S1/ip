package duke.command;

import duke.duke.Duke;

public class DeadlineCommand extends Command {


  private final String desc;
  private final String by;

  public DeadlineCommand(String desc, String by) {
    this.desc = desc;
    this.by = by;
  }

  @Override
  public void execute(Duke duke) throws Exception {
    duke.addDeadlineTask(desc, by);
  }

  @Override
  public void undo(Duke duke) {
    // stub
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
