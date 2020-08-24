package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

/** EventCommand adds an event to the task list */
public class EventCommand extends CreateCommand {
  public EventCommand(String commandString) {
    super(CommandType.EVENT, commandString);
  }

  /**
   * Adds an event to the task list
   *
   * @param tasks task list of tasks
   * @throws DukeException when the command string has no valid date
   */
  public void execute(TaskList tasks) throws DukeException {
    String description = super.getTaskDescription();
    String[] taskParts = description.split(" /at ", 2);

    if (taskParts.length != 2 || taskParts[1].equals("")) {
      throw Ui.taskDateEmptyException(super.getCommandType());
    }

    String name = taskParts[0];
    String dateString = taskParts[1];
    super.addTask(tasks, new Event(name, Parser.parseDateString(dateString)));
  }
}
