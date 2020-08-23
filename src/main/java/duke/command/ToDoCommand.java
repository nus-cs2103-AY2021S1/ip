package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;

public class ToDoCommand extends CreateCommand {
  public ToDoCommand(String commandString) {
    super(CommandType.TODO, commandString);
  }

  @Override
  public void execute(TaskList tasks) throws DukeException {
    String description = this.getTaskDescription();
    this.addTask(tasks, new ToDo(description));
  }
}
