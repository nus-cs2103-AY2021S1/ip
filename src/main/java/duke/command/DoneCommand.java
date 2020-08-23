package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends UpdateCommand {
  public DoneCommand(String commandString) {
    super(CommandType.DONE, commandString);
  }

  @Override
  public void execute(TaskList tasks) throws DukeException {
    int index = super.getTaskTargetIndex();
    Task task = tasks.get(index);
    task.setDone(true);
    Ui.showDone(task);
  }
}
