package duke.command;

import duke.duke.Duke;
import duke.task.Task;
import java.util.List;

public class ListTasksCommand extends Command {

  @Override
  public void execute(Duke duke) {
    List<Task> tasks = duke.getTasks();
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
