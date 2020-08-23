package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends UpdateCommand {
  public DeleteCommand(String commandString) {
    super(CommandType.DELETE, commandString);
  }

  @Override
  public void execute(TaskList tasks) throws DukeException {
    int index = super.getTaskTargetIndex();
    Task task = tasks.remove(index);
    Ui.showDelete(task);
  }
}
