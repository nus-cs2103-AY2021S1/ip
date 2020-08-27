package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.io.Storage;
import duke.io.TaskList;

/**
 * Delete command type. Remove task from task arraylist.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class DeleteCommand extends Command {

  private final String fullCommand;

  /**
   * Class constructor. Extract task index details from full command.
   *
   * @param fullCommand full command input by user.
   */
  public DeleteCommand(String fullCommand) {
    this.fullCommand = fullCommand;
  }

  /**
   * Remove task with index from task arraylist. Write to file.
   *
   * @param taskList arraylist of task.
   * @param ui ui class for print.
   * @param storage storage for read, write to file.
   * @throws DukeException unable to delete item.
   */
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

  /**
   * Indicator for application to end.
   *
   * @return false.
   */
  @Override
  public boolean isExit() {
    return false;
  }
}
