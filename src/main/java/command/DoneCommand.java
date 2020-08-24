package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.InvalidInputException;
import task.Task;

public class DoneCommand extends Command {
  int index;

  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
    if (index != 0 && index <= taskList.getSize()) {
      Task task = taskList.get(index - 1);
      task.MarkAsDone();
      ui.showDone(task);
    } else {
      throw new InvalidInputException(
          "Number provided is too small or too large, Please provide a valid task number");
    }
  }

  public DoneCommand(int index) {
    super(CommandType.Done);
    this.index = index;
  }
}
