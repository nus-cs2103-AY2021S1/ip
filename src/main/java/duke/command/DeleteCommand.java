package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.io.Storage;
import duke.io.TaskList;

public class DeleteCommand extends Command {

  private final String fullCommand;

  public DeleteCommand(String fullCommand) {
    this.fullCommand = fullCommand;
  }

  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
    ui.showLine();
    String[] input = fullCommand.split(" ");
    System.out.println("Noted. I've removed this task: ");
    if (input.length == 2) {
      int index = Integer.parseInt(input[1]);
      System.out.printf("\t%s\n", taskList.retrieveTask(index - 1));
      taskList.deleteTask(Integer.parseInt(input[1]) - 1);
    } else {
      throw new DukeException("Cannot delete item!");
    }
    System.out.printf("Now you have %o tasks in the list\n", taskList.sizeOfList());
    ui.showLine();

    storage.write(taskList);
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
