package duke.command;

import duke.Ui;
import duke.io.Storage;
import duke.io.TaskList;

public class ByeCommand extends Command {

  @Override
  public void execute(TaskList task, Ui ui, Storage storage) {
    ui.showLine();
    System.out.println("Bye. Hope to see you again soon!");
    ui.showLine();
  }

  @Override
  public boolean isExit() {
    return true;
  }
}
