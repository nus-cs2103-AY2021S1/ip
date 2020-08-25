package duke.command;

import duke.Ui;
import duke.io.Storage;
import duke.io.TaskList;
import duke.task.Todo;

public class ToDoCommand extends Command {

  private final String fullCommand;

  public ToDoCommand(String fullCommand) {
    this.fullCommand = fullCommand;
  }

  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage) {
    ui.showLine();
    String description = fullCommand.substring(fullCommand.indexOf(" ")).trim();
    System.out.println("Got it. I've added this task:");
    taskList.addTask(new Todo(description));
    System.out.println("\t" + taskList.retrieveTask(taskList.sizeOfList() - 1));
    System.out.printf("Now you have %o tasks in list.\n", taskList.sizeOfList());
    ui.showLine();

    storage.write(taskList);
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
