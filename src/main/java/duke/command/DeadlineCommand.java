package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends CreateCommand {
  public DeadlineCommand(String commandString) {
    super(CommandType.DEADLINE, commandString);
  }

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
