package duke.command;

import duke.duke.Duke;

public class CompleteTaskCommand extends Command {

  int taskId;

  public CompleteTaskCommand(int taskId) {
    this.taskId = taskId;
  }

  @Override
  public void execute(Duke duke) throws Exception {
    duke.markTaskDone(taskId);
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
