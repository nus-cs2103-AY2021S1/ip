package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * UpdateCommand is the abstract superclass of all command classes that requires adding of new items
 * into the task list
 */
public abstract class CreateCommand extends Command {
  public CreateCommand(CommandType commandType, String commandString) {
    super(commandType, commandString);
  }

  /**
   * Adds a task into the task list and prints the task added
   *
   * @param tasks task list of task
   * @param task the task to be added
   */
  public void addTask(TaskList tasks, Task task) {
    tasks.add(task);
    Ui.showAddTask(task);
  }
}
