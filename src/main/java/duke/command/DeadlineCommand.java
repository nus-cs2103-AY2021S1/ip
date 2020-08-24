package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * EventCommand adds a deadline task to the task list
 */
public class DeadlineCommand extends CreateCommand {
  public DeadlineCommand(String commandString) {
    super(CommandType.DEADLINE, commandString);
  }

  /**
   * Adds a deadline event to the task list
   *
   * @param tasks task list of tasks
   * @throws DukeException when the command string has no valid date
   */
  @Override
  public void execute(TaskList tasks) throws DukeException {
    String description = super.getTaskDescription();
    String[] taskParts = description.split(" /by ", 2);

    if (taskParts.length != 2 || taskParts[1].equals("")) {
      throw Ui.taskDateEmptyException(super.getCommandType());
    }

    String name = taskParts[0];
    String dateString = taskParts[1];
    super.addTask(tasks, new Deadline(name, Parser.parseDateString(dateString)));
  }
}
