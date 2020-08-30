package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

public class ListCommand extends Command {

  @Override
  public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
    return ui.displayTaskList(taskList);
  }

  public ListCommand() {
    super(Command.CommandType.List);
  }
}
