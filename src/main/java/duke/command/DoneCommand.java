package duke.command;

import duke.Ui;
import duke.io.Storage;
import duke.io.TaskList;
import duke.task.Task;

public class DoneCommand extends Command {

  private final String fullCommand;

  public DoneCommand(String fullCommand) {
    this.fullCommand = fullCommand;
  }

  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage) {
    ui.showLine();
    String[] input = fullCommand.split(" ");
    Task task = taskList.retrieveTask(Integer.parseInt(input[1]));
    task.markAsDone();
    System.out.println("Nice! I've marked this task as done:");
    System.out.printf("%s\n", task);
    ui.showLine();

    // add write to file method
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
