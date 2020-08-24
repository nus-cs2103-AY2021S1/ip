package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

public abstract class Command {
  public CommandType type;

  public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

  public boolean isExit() {
    return this.type.equals(CommandType.Bye);
  }

  public enum CommandType {
    Bye,
    Todo,
    Deadline,
    Event,
    Done,
    Delete,
    List
  }

  Command(CommandType type) {
    this.type = type;
  }
}
