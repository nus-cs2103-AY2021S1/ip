package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
  public ListCommand(String commandString) {
    super(CommandType.LIST, commandString);
  }

  @Override
  public void execute(TaskList tasks) {
    Ui.showList(tasks);
  }
}
