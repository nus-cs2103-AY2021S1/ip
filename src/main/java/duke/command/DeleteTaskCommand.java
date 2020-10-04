package duke.command;

import duke.duke.Duke;

public class DeleteTaskCommand extends Command {
  int taskId;

  public DeleteTaskCommand(int taskId) {
    this.taskId = taskId;
  }

  @Override
  public void execute(Duke duke)  throws Exception{
    // TODO: make this duke.deleteTask return pos info for undo operation
    duke.deleteTask(taskId);
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
