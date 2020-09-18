package duke.command;

import duke.duke.Duke;

public class EventCommand extends Command {

  private final String desc;
  private final String at;

  public EventCommand(String desc, String at) {
    this.desc = desc;
    this.at = at;
  }

  @Override
  public void execute(Duke duke) throws Exception {
    duke.addEventTask(desc, at);
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
