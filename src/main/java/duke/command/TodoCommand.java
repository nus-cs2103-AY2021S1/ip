package duke.command;

import duke.duke.Duke;

public class TodoCommand extends Command {

  private final String desc;

  public TodoCommand(String desc) {
    this.desc = desc;
  }

  @Override
  public void execute(Duke duke) {
    duke.addTodoTask(desc);
  }

  @Override
  public void undo(Duke duke) {
    // stub
  }
}
