package duke.command;

import duke.duke.Duke;

public class CompleteTaskCommand extends Command {

  int taskId;

  public CompleteTaskCommand(int taskId) {
    this.taskId = taskId;
  }

  @Override
  public void execute(Duke duke) {
    duke.markTaskDone(taskId);
  }

  @Override
  public void undo(Duke duke) {
    // stub
  }
}
